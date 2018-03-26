/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.MyBatisUtils;
import model.pojos.Contacto;
import org.apache.ibatis.session.SqlSession;

/**
 * Objeto de Acceso de Datos de contactos para MySQL
 * @author Manolo
 * @since 03/10/2018
 * @version 1.0
 */
public class ContactoDAO {
    /**
     * Buscador de todos los Contactos almacenados
     * @return Lista de los contactos encontrados
     */
    public static List<Contacto> getAllContactos() {
        List<Contacto> lista = new ArrayList<Contacto>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            lista = conn.selectList("Contacto.getAllContactos");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lista;
    }

    /**
     * Buscador de un contacto en específico por su nombre
     * @param nombre Nombre del contacto buscado
     * @return Lista de los contactos encontrados
     */
    public static List<Contacto> buscarContacto(String nombre) {
        List<Contacto> lista = new ArrayList<Contacto>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            lista = conn.selectList("Contacto.buscarContacto", nombre);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lista;
    }

    /**
     * Se almacena un nuevo contacto en la Base de Datos
     * @param contacto Objeto Contacto para registrarlo
     * @return Confirmación si se pudo registrar el Contacto con éxito
     */
    public static boolean registrar(Contacto contacto) {
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("Contacto.registrar", contacto);
            conn.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    /**
     * Se actualiza un contacto ya registrado en la Base de Datos
     * @param contacto Objeto Contacto con un idContacto definido
     * @return Confirmación si se pudo actualizar el Contacto con éxito
     */
    public static boolean actualizar(Contacto contacto) {
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.update("Contacto.actualizar", contacto);
            conn.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    /**
     * Se elimina un Contacto de la Base de Datos a partir de su idContacto
     * @param idContacto Identificador de un contacto ya registrado en la Base de Datos
     * @return Confirmación si se pudo eliminar el Contacto con éxito
     */
    public static boolean eliminar(int idContacto) {
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.delete("Contacto.eliminar", idContacto);
            conn.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
