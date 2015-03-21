package com.gadroves.gsisinve.model.daos;

import com.gadroves.gsisinve.model.DataBase;
import com.gadroves.gsisinve.model.beans.Cliente_Cuenta;
import com.gadroves.gsisinve.model.beans.Factura_Venta;
import com.gadroves.gsisinve.model.beans.Linea_Factura;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.*;
import com.gadroves.gsisinve.utils.Dataformat;
import com.gadroves.gsisinve.utils.DateConvertUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Casa on 18/03/2015.
 */
//TODO Terminmar
public class Factura_VentaDAO {
    private static Factura_VentaDAO instance = new Factura_VentaDAO();
    public static ResultSetProcessor<Factura_Venta> processor = (rs, rw) -> {
        return new Factura_Venta(rs.getInt("id"),new Date(rs.getDate("fac_date").getTime()),rs.getDouble("tax"),rs.getDouble("sub"),rs.getDouble("total"),rs.getNString("address"));
    };

    public  static Factura_VentaDAO instance(){
        return instance;
    }
    public IntermediateFacturaVentaSelect select() {
        return new IntermediateFacturaVentaSelect();
    }
    public IntermediateFacturaVentaUpdate update() {
        return new IntermediateFacturaVentaUpdate();
    }
    public IntermediateFacturaVentaInsert insert() {
        return new IntermediateFacturaVentaInsert();
    }
    public IntermediateFacturaVentaDelete delete() {
        return new IntermediateFacturaVentaDelete();
    }


    public class IntermediateFacturaVentaSelect implements IntermediateSelect<Factura_Venta, Integer>{
        @Override
        public List<Factura_Venta> all() {
            ArrayList<Factura_Venta> facturas = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                String sql = "Select * FROM TB_Factura_Venta";
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()){
                    Factura_Venta factura_venta = processor.process(rs,0);
                    List<Linea_Factura> lineas = LineaFacturaVentaDAO.instance().select().byFactura(factura_venta);
                    factura_venta.getLineas().addAll(lineas);
                    factura_venta.setCliente_factura(Cliente_Factura_DAO.getInstance().select().byID(factura_venta));
                    facturas.add(factura_venta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return facturas;
        }

        @Override
        public Factura_Venta byID(Integer pk) {
            Factura_Venta factura_venta = null;
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                String sql = "Select * FROM TB_Factura_Venta WHERE id = " + pk;
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()){
                    factura_venta = processor.process(rs,0);
                    List<Linea_Factura> lineas = LineaFacturaVentaDAO.instance().select().byFactura(factura_venta);
                    factura_venta.getLineas().addAll(lineas);
                    factura_venta.setCliente_factura(Cliente_Factura_DAO.getInstance().select().byID(factura_venta));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return factura_venta;
        }

        //Warning : Llamado muy Pesado Uselo con cuidado
        @Override
        public List<Factura_Venta> where(Predicate<Factura_Venta>... pl) {
            if(pl.length==0) return all();
            Predicate<Factura_Venta> ini = pl[0]; for(int i=1; i<pl.length; i++) ini = ini.and(pl[i]);
            return all().stream().filter(ini).collect(Collectors.toList());
        }

        public List<Factura_Venta> byClientName(String nombreCliente){
            List<Factura_Venta> factura_ventas = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()){
                String facIdSql = "SELECT * FROM TB_Factura_Venta tfv WHERE tfv.id IN (SELECT fac_id FROM TB_CLiente_Factura tcf WHERE tcf.name LIKE \'%"+ nombreCliente +"%\')";
                Statement stm = c.createStatement();
                ResultSet rs = stm.executeQuery(facIdSql);
                while (rs.next()){
                    Factura_Venta factura_venta;
                    factura_venta = processor.process(rs,0);
                    List<Linea_Factura> lineas = LineaFacturaVentaDAO.instance().select().byFactura(factura_venta);
                    factura_venta.getLineas().addAll(lineas);
                    factura_venta.setCliente_factura(Cliente_Factura_DAO.getInstance().select().byID(factura_venta));
                    factura_ventas.add(factura_venta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return factura_ventas;
        }
        public List<Factura_Venta> byClientNameNDate(String nombreCliente,Date d){
            return byClientName(nombreCliente).stream().filter((fv) -> {
                try {
                    return DateConvertUtils.asLocalDate(fv.getFechaD()).compareTo(DateConvertUtils.asLocalDate(d)) == 0;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());

        }

        public List<Factura_Venta> byCuentaCliente(String clientID){
            List<Factura_Venta> factura_ventas = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()){
                String facIdSql = "SELECT * FROM TB_Factura_Venta tfv WHERE tfv.id IN (SELECT tc.factura_asociada FROM TB_Cobro tc WHERE tc.cuenta_cliente ="+ Dataformat.asSqlString(clientID)+ ");";
                Statement stm = c.createStatement();
                ResultSet rs = stm.executeQuery(facIdSql);
                while (rs.next()){
                    Factura_Venta factura_venta;
                    factura_venta = processor.process(rs,0);
                    List<Linea_Factura> lineas = LineaFacturaVentaDAO.instance().select().byFactura(factura_venta);
                    factura_venta.getLineas().addAll(lineas);
                    factura_venta.setCliente_factura(Cliente_Factura_DAO.getInstance().select().byID(factura_venta));
                    factura_ventas.add(factura_venta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return factura_ventas;
        }
        public List<Factura_Venta> byCuentaClienteNDate(Cliente_Cuenta Cliente,Date d){
            return byCuentaCliente(Cliente.getCodigo()).stream().filter((fv) -> {
                try {
                    return DateConvertUtils.asLocalDate(fv.getFechaD()).compareTo(DateConvertUtils.asLocalDate(d)) == 0;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());
        }
        public List<Factura_Venta> betweenDates(Date d1,Date d2){
            ArrayList<Factura_Venta> facturas = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                LocalDate ld1 = DateConvertUtils.asLocalDate(d1);
                LocalDate ld2 = DateConvertUtils.asLocalDate(d2);
                String sql = "Select * FROM TB_Factura_Venta fv WHERE fv.fac_date BETWEEN "+  Dataformat.asSqlString(ld1.toString()) +" AND "+ Dataformat.asSqlString(ld2.toString());
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()){
                    Factura_Venta factura_venta = processor.process(rs,0);
                    List<Linea_Factura> lineas = LineaFacturaVentaDAO.instance().select().byFactura(factura_venta);
                    factura_venta.getLineas().addAll(lineas);
                    factura_venta.setCliente_factura(Cliente_Factura_DAO.getInstance().select().byID(factura_venta));
                    facturas.add(factura_venta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return facturas;
        }
    }
    public class IntermediateFacturaVentaUpdate implements IntermediateUpdate<Factura_Venta, Integer> {
        @Override
        public boolean updateSingle(Factura_Venta reference) {
            return false;
        }

        @Override
        public boolean updateCollection(Collection<Factura_Venta> collection) {
            return false;
        }

        @Override
        public boolean updateIf(Factura_Venta ref, Predicate<Factura_Venta>... what) {
            return false;
        }

        @Override
        public boolean updateCollectionWhen(Collection<Factura_Venta> refs, Predicate<Factura_Venta>... what) {
            return false;
        }
    }
    public class IntermediateFacturaVentaInsert implements IntermediateInsert<Factura_Venta> {
        @Override
        public boolean single(Factura_Venta ref) {
            return false;
        }

        @Override
        public boolean aCollection(Collection<Factura_Venta> refs) {
            return false;
        }

        @Override
        public boolean aCollectionWhere(Collection<Factura_Venta> refs, Predicate<Factura_Venta>... conds) {
            return false;
        }
    }
    public class IntermediateFacturaVentaDelete implements IntermediateDelete<Factura_Venta> {
        @Override
        public void single(Factura_Venta ref) {
        }

        @Override
        public void aCollection(Collection<Factura_Venta> refs) {
        }

        @Override
        public void aCollectionWhere(Collection<Factura_Venta> refs, Predicate<Factura_Venta>... conds) {
        }
    }

}
