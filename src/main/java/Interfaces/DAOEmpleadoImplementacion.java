package Interfaces;

import Dao.Main;
import Personas.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DAOEmpleadoImplementacion implements DAOEmpleado{
    
    //Establecer una instancia de la clase main 
    
    Main main = new Main();
    
    public DAOEmpleadoImplementacion(){}

    
    @Override
public void registrar(Empleado empleado) {
    boolean registroExitoso = false;
    try {
        Connection conectar = main.establecerConeccion();

        // Verificar si el ID ya está en uso
        PreparedStatement verificarId = conectar.prepareStatement("SELECT id FROM empleados WHERE id = ?");
        verificarId.setInt(1, empleado.getId());
        ResultSet resultSet = verificarId.executeQuery();

        if (resultSet.next()) {
            // El ID ya está en uso, mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "El ID ya está en uso. Por favor, elija otro.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (empleado.getId() <= 0 || empleado.getEmpleado().isEmpty()) {
            // Faltan datos, mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos antes de registrar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Insertar el nuevo registro si no hay problemas
            PreparedStatement insertar = conectar.prepareStatement("INSERT INTO empleados (id, nombre) VALUES(?, ?)");
            insertar.setInt(1, empleado.getId());
            insertar.setString(2, empleado.getEmpleado());
            insertar.executeUpdate();

            // Registro exitoso
            registroExitoso = true;
        }
    } catch (Exception e) {
        // Mostrar mensaje de error general
        JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (registroExitoso) {
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Registro ingresado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

public void modificar(Empleado empleado) {
    try {
        Connection conectar = main.establecerConeccion();
        PreparedStatement modificar = conectar.prepareStatement("UPDATE empleados SET nombre = ? WHERE id = ?");
        modificar.setString(1, empleado.getEmpleado()); 
        modificar.setInt(2, empleado.getId());        

        modificar.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();  
    }
}

   public void eliminar(Empleado empleado){
       try{
           
           Connection conectar = main.establecerConeccion();
           
           PreparedStatement eliminar = conectar.prepareStatement("DELETE FROM empleados where id = ?");
           
           eliminar.setInt(1, empleado.getId());
           
           eliminar.executeUpdate();
           
       }catch(Exception e){
           System.out.println(e);
       }
   }

    @Override
    public void buscar(Empleado empleado) {
        try{
           
           Connection conectar = main.establecerConeccion();
           
           PreparedStatement buscar = conectar.prepareStatement("select * from empleados where id = ?");
           
           buscar.setInt(1, empleado.getId());
           
           ResultSet consulta = buscar.executeQuery();
           
           if( consulta.next() ){
               empleado.setId(Integer.parseInt(consulta.getString("id")));
               empleado.setEmpleado(consulta.getString("nombre"));
           }
           
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
    
}