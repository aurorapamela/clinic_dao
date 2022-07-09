package com.dh.C23.dao;

import com.dh.C23.dominio.Odontologo;
import com.dh.C23.dominio.Paciente;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoDAOH2 implements IDao<Odontologo> {
    @Override
    public List<Odontologo> listarElementos() {
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologo;
        try {
            connection = H2Aux.getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                listaOdontologos.add(odontologo);
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
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarXId(int id) {
        return null;
    }

    @Override
    public Odontologo buscarXCriterio(String criterio) {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGOS (APELLIDO, NOMBRE, MATRICULA) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, odontologo.getApellido());
            ps.setString(2, odontologo.getNombre());
            ps.setInt(3, odontologo.getMatricula());
            ps.execute();
            ResultSet clave = ps.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
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
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return null;
    }
}
