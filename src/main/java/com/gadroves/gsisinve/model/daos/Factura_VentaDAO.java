package com.gadroves.gsisinve.model.daos;

import com.gadroves.gsisinve.model.beans.Cliente_Cuenta;
import com.gadroves.gsisinve.model.beans.Factura_Venta;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Casa on 18/03/2015.
 */
//TODO Terminmar
public class Factura_VentaDAO {

    public static ResultSetProcessor<Factura_Venta> processor = (rs, rw) -> {
        return new Factura_Venta(rs.getInt("id"),new Date(rs.getDate("fac_date").getTime()),rs.getDouble("tax"),rs.getDouble("sub"),rs.getDouble("total"),rs.getNString("address"));
    };

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
            return null;
        }

        @Override
        public Factura_Venta byID(Integer pk) {
            return null;
        }

        @Override
        public List<Factura_Venta> where(Predicate<Factura_Venta>... pl) {
            return null;
        }

        public List<Factura_Venta> byClientName(String nombreCliente){
            return null;
        }
        public List<Factura_Venta> byClientNameNDate(String nombreCliente,Date d){
            return null;
        }

        public List<Factura_Venta> byCuentaCliente(String nombreCliente){
            return null;
        }
        public List<Factura_Venta> byCuentaClienteNDate(Cliente_Cuenta Cliente,Date d){
            return null;
        }
        public List<Factura_Venta> betweenDates(Date d1,Date d2){
            return null;
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
