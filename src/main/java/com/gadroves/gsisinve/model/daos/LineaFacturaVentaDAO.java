package com.gadroves.gsisinve.model.daos;

import com.gadroves.gsisinve.model.beans.Factura_Venta;
import com.gadroves.gsisinve.model.beans.Linea_Factura;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateDelete;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateInsert;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateSelect;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateUpdate;
import com.gadroves.gsisinve.utils.TwoTuple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Casa on 18/03/2015.
 */

//TODO NExT para terminar
public class LineaFacturaVentaDAO {

    private static LineaFacturaVentaDAO instance = null;

    private LineaFacturaVentaDAO(){

    }

    public static LineaFacturaVentaDAO instance(){
        if(instance == null) instance = new LineaFacturaVentaDAO();
        return instance;
    }

    public IntermediateLineaFacturaVentaSelect select() {
        return new IntermediateLineaFacturaVentaSelect();
    }
    public IntermediateLineaFacturaVentaUpdate update() {
        return new IntermediateLineaFacturaVentaUpdate();
    }
    public IntermediateLineaFacturaVentaInsert insert() {
        return new IntermediateLineaFacturaVentaInsert();
    }
    public IntermediateLineaFacturaVentaDelete delete() {
        return new IntermediateLineaFacturaVentaDelete();
    }

    public class IntermediateLineaFacturaVentaSelect implements IntermediateSelect<Linea_Factura,TwoTuple<Integer,String>>{
        @Override
        public List<Linea_Factura> all() {
            return null;
        }

        @Override
        public Linea_Factura byID(TwoTuple<Integer, String> pk) {
            return null;
        }

        @Override
        public List<Linea_Factura> where(Predicate<Linea_Factura>... pl) {
            return null;
        }

        public List<Linea_Factura> byFactura(Factura_Venta fb){
            List<Linea_Factura> lineas = new ArrayList<>();
            return lineas;
        }
    }
    public class IntermediateLineaFacturaVentaUpdate implements IntermediateUpdate<Linea_Factura, TwoTuple<Integer,String>> {
        @Override
        public boolean updateSingle(Linea_Factura reference) {
            return false;
        }

        @Override
        public boolean updateCollection(Collection<Linea_Factura> collection) {
            return false;
        }

        @Override
        public boolean updateIf(Linea_Factura ref, Predicate<Linea_Factura>... what) {
            return false;
        }

        @Override
        public boolean updateCollectionWhen(Collection<Linea_Factura> refs, Predicate<Linea_Factura>... what) {
            return false;
        }
    }
    public class IntermediateLineaFacturaVentaInsert implements IntermediateInsert<Linea_Factura>{
        @Override
        public boolean single(Linea_Factura ref) {
            return false;
        }

        @Override
        public boolean aCollection(Collection<Linea_Factura> refs) {
            return false;
        }

        @Override
        public boolean aCollectionWhere(Collection<Linea_Factura> refs, Predicate<Linea_Factura>... conds) {
            return false;
        }
    }
    public class IntermediateLineaFacturaVentaDelete implements IntermediateDelete<Linea_Factura> {
        @Override
        public boolean single(Linea_Factura ref) {
            return false;
        }

        @Override
        public void aCollection(Collection<Linea_Factura> refs) {
        }

        @Override
        public void aCollectionWhere(Collection<Linea_Factura> refs, Predicate<Linea_Factura>... conds) {
        }
    }
}
