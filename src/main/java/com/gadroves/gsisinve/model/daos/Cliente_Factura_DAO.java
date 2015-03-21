package com.gadroves.gsisinve.model.daos;

import com.gadroves.gsisinve.model.beans.Cliente_Factura;
import com.gadroves.gsisinve.model.beans.Factura_Venta;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateDelete;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateInsert;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateSelect;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateUpdate;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Casa on 20/03/2015.
 */
//TODO Definir Metodos
public class Cliente_Factura_DAO {
    private static Cliente_Factura_DAO ourInstance = new Cliente_Factura_DAO();

    public static Cliente_Factura_DAO getInstance() {
        return ourInstance;
    }

    private Cliente_Factura_DAO() {
    }

    public IntermediateClientFactSelect select(){ return new IntermediateClientFactSelect();}
    public IntermediateClientFactUpdate update(){ return new IntermediateClientFactUpdate();}
    public IntermediateClientFactInsert insert(){ return new IntermediateClientFactInsert();}
    public IntermediateClientFactDelete delete(){ return new IntermediateClientFactDelete();}

    public class IntermediateClientFactSelect implements IntermediateSelect<Cliente_Factura,Factura_Venta>{
        @Override
        public List<Cliente_Factura> all() {
            return null;
        }

        @Override
        public Cliente_Factura byID(Factura_Venta pk) {
            return null;
        }

        @Override
        public List<Cliente_Factura> where(Predicate<Cliente_Factura>... pl) {
            return null;
        }
    }
    public class IntermediateClientFactUpdate implements IntermediateUpdate<Cliente_Factura,Factura_Venta>{
        @Override
        public boolean updateSingle(Cliente_Factura reference) {
            return false;
        }

        @Override
        public boolean updateCollection(Collection<Cliente_Factura> collection) {
            return false;
        }

        @Override
        public boolean updateIf(Cliente_Factura ref, Predicate<Cliente_Factura>... what) {
            return false;
        }

        @Override
        public boolean updateCollectionWhen(Collection<Cliente_Factura> refs, Predicate<Cliente_Factura>... what) {
            return false;
        }
    }
    public class IntermediateClientFactInsert implements IntermediateInsert<Cliente_Factura>{
        @Override
        public boolean single(Cliente_Factura ref) {
            return false;
        }

        @Override
        public boolean aCollection(Collection<Cliente_Factura> refs) {
            return false;
        }

        @Override
        public boolean aCollectionWhere(Collection<Cliente_Factura> refs, Predicate<Cliente_Factura>... conds) {
            return false;
        }
    }
    public class IntermediateClientFactDelete implements IntermediateDelete<Cliente_Factura>{
        @Override
        public boolean single(Cliente_Factura ref) {
            return false;
        }

        @Override
        public void aCollection(Collection<Cliente_Factura> refs) {
        }

        @Override
        public void aCollectionWhere(Collection<Cliente_Factura> refs, Predicate<Cliente_Factura>... conds) {
        }
    }


}
