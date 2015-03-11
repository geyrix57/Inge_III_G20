package com.gadroves.gsisinve.model.daos;

import com.gadroves.gsisinve.model.DataBase;
import com.gadroves.gsisinve.model.beans.Inventario;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateSelect;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.IntermediateUpdate;
import com.gadroves.gsisinve.model.daos.DAOInterfaces.ResultSetProcessor;
import com.gadroves.gsisinve.utils.TwoTuple;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Casa on 14/02/2015.
 */
public class InventarioDAO {
    private static InventarioDAO instance = null;
    public static ResultSetProcessor<Inventario> processor = (rs, rw) -> {
        Inventario inventario = new Inventario(rs.getNString("code_art"), rs.getNString("code_bod"), rs.getInt("quant"), rs.getInt("max_quant"), rs.getInt("min_quant"));
        return inventario;
    };

    public static InventarioDAO getInstance() {
        if (instance == null) instance = new InventarioDAO();
        return instance;
    }

    public IntermediateInventorySelect select() {
        return new IntermediateInventorySelect();
    }
    public IntermediateInventoryUpdate update() {
        return new IntermediateInventoryUpdate();
    }


    public class IntermediateInventorySelect implements IntermediateSelect<Inventario, TwoTuple<String, String>> {
        @Override
        final public List<Inventario> all() {
            ArrayList<Inventario> items = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                retrieveAll(stm, items);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return items;
        }

        @Override
        public Inventario byID(TwoTuple<String, String> pk) {
            Inventario inv = null;
            try (Connection c = DataBase.getInstance().getConnection()) {
                String sql = "SELECT * FROM TB_Inventario inv WHERE inv.code_art = ? AND inv.code_bod = ?";
                PreparedStatement pstm = c.prepareStatement(sql);
                pstm.setString(1, pk.getFirst());
                pstm.setString(2, pk.getSecond());
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    inv = processor.process(rs, 0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return inv;
        }

        private void retrieveAll(Statement statement, List<Inventario> repo) throws SQLException {
            ResultSet rs = statement.executeQuery("SELECT * FROM TB_Inventario");
            Inventario inv;
            while (rs.next()) {
                inv = processor.process(rs, 0);
                if (inv != null) repo.add(inv);
            }
        }

        @Override
        @SafeVarargs
        final public List<Inventario> where(Predicate<Inventario>... pl) {
            if(pl.length==0) return all();
            Predicate<Inventario> ini = pl[0]; for(int i=1; i<pl.length; i++) ini = ini.and(pl[i]);
            return all().stream().filter(ini).collect(Collectors.toList());
        }
    }
    public class IntermediateInventoryUpdate implements IntermediateUpdate<Inventario,TwoTuple<String, String>>{
        @Override
        public boolean updateSingle(Inventario reference) {
            String query = "UPDATE TB_Inventario SET quant = ?,min_quant = ?, max_quant = ? WHERE code_art = ? AND code_bod = ? ";
            try (Connection c = DataBase.getInstance().getConnection()) {
                PreparedStatement stm = c.prepareStatement(query);
                stm.setInt(1, reference.getCamtidad());
                stm.setInt(2,reference.getCantidad_min());
                stm.setInt(3,reference.getCantidad_max());
                stm.setString(4,reference.getCodigo_articulo());
                stm.setString(5,reference.getCodigo_articulo());
                return stm.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean updateCollection(Collection<Inventario> collection) {
            if(collection.isEmpty()) return false;
            boolean flag = false;
            String query = "UPDATE TB_Inventario SET quant = ?,min_quant = ?, max_quant = ? WHERE code_art = ? AND code_bod = ? ";
            try (Connection c = DataBase.getInstance().getConnection()) {
                PreparedStatement stm = c.prepareStatement(query);
                for(Inventario reference : collection) {
                    stm.setInt(1, reference.getCamtidad());
                    stm.setInt(2, reference.getCantidad_min());
                    stm.setInt(3, reference.getCantidad_max());
                    stm.setString(4, reference.getCodigo_articulo());
                    stm.setString(5, reference.getCodigo_articulo());
                    if(stm.executeUpdate() > 0) flag = true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        }

        @Override
        public boolean updateIf(Inventario ref, Predicate<Inventario>... what) {
            if (what.length == 0) return updateSingle(ref);
            Predicate<Inventario> ini = what[0];
            for (int i = 1; i < what.length; i++) ini = ini.and(what[i]);
            return  ini.test(ref) ? updateSingle(ref) : false;
        }

        @Override
        public boolean updateCollectionWhen(Collection<Inventario> refs, Predicate<Inventario>... what) {
            if (what.length == 0) return updateCollection(refs);
            Predicate<Inventario> ini = what[0];
            for (int i = 1; i < what.length; i++) ini = ini.and(what[i]);
            List<Inventario> oks = refs.stream().filter(ini).collect(Collectors.toList());
            return updateCollection(oks);
        }
    }
}
