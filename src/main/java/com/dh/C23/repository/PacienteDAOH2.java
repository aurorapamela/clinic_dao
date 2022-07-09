package com.dh.C23.repository;

import com.dh.C23.dominio.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDAOH2 implements IDao<Paciente>{
    @Override
    public List<Paciente> listarElementos() {
        Connection connection = null;
        List<Paciente> listaPacientes = new ArrayList<>();
        Paciente paciente;
        try {
            connection = H2Aux.getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES");
            ResultSet  rs = ps.executeQuery();
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate(),
                        domicilioDAOH2.buscarXId(rs.getInt(7))); //acá le pido que busque por id lo que me trae el dao-domicilio
                listaPacientes.add(paciente);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return listaPacientes;
    }

    @Override
    public Paciente buscarXId(int id) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = H2Aux.getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            PreparedStatement ps = connection.prepareStatement("SELECT FROM PACIENTES WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet  rs = ps.executeQuery();
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate(),
                        domicilioDAOH2.buscarXId(rs.getInt(7))); //acá le pido que busque por id lo que me trae el dao-domicilio
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
        return paciente;
    }

    @Override
    public Paciente buscarXCriterio(String criterio) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = H2Aux.getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            PreparedStatement ps = connection.prepareStatement("SELECT FROM PACIENTES WHERE EMAIL = ?");
            ps.setString(1, criterio);
            ResultSet  rs = ps.executeQuery();
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate(),
                        domicilioDAOH2.buscarXId(rs.getInt(7))); //acá le pido que busque por id lo que me trae el dao-domicilio
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
        return paciente;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try {
            Paciente pacienteAEliminar = buscarXId(id);
            connection = H2Aux.getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            domicilioDAOH2.eliminar(pacienteAEliminar.getDomicilio().getId());
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PACIENTES WHERE ID = ?");

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
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        try {
            connection = H2Aux.getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            domicilioDAOH2.guardar(paciente.getDomicilio());
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PACIENTES " +
                    "(APELLIDO, NOMBRE, EMAIL, DNI, FECHA_INGRESO, DOMICILIO_ID)" +
                    "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getApellido());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getEmail());
            ps.setInt(4, paciente.getDni());
            ps.setDate(5, Date.valueOf(paciente.getFecha_ingreso()));
            ps.setInt(6, paciente.getDomicilio().getId());
            ps.execute();
            ResultSet clave = ps.getGeneratedKeys();
            while(clave.next()){
                paciente.setId(clave.getInt(1));
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
        return paciente;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;
        try {
            connection = H2Aux.getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            domicilioDAOH2.actualizar(paciente.getDomicilio());
            PreparedStatement ps = connection.prepareStatement("UPDATE PACIENTES " +
                    "SET APELLIDO=?, NOMBRE=?, EMAIL=?, DNI=?, FECHA_INGRESO=?, DOMICILIO_ID = ? WHERE ID=?");
            ps.setString(1, paciente.getApellido());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getEmail());
            ps.setInt(4, paciente.getDni());
            ps.setDate(5, Date.valueOf(paciente.getFecha_ingreso()));
            ps.setInt(6, paciente.getDomicilio().getId());
            ps.setInt(7, paciente.getId());
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
        return paciente;
    }
}
