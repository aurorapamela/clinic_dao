package com.dh.C23.repository;

import com.dh.C23.dominio.Domicilio;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class DomicilioDAOH2 implements IDao<Domicilio>{

    @Override
    public List<Domicilio> listarElementos() {
        return null;
    }

    @Override
    public Domicilio buscarXId(int id) {
        Connection connection = null;
        Domicilio domicilio = null;
        try {
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM DOMICILIOS" +
                    "WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5)); //acá le pido que busque por id lo que me trae el dao-domicilio
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscarXCriterio(String criterio) {
        return null;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try {
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM DOMICILIOS WHERE ID = ?");
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;
        try {
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO DOMICILIOS " +
                    "(CALLE, NUMERO, LOCALIDAD, PROVINCIA)" +
                    "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.execute();
            ResultSet clave = ps.getGeneratedKeys();
            while(clave.next()){
                domicilio.setId(clave.getInt(1));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;
        try {
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE DOMICILIOS " +
                    "SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID = ?");
            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.setInt(5, domicilio.getId());
            ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }
}
