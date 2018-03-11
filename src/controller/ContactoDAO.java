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
 *
 * @author Manolo
 */
public class ContactoDAO {

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

    public static List<Contacto> buscarContacto(String nombre) {
        List<Contacto> lista = new ArrayList<Contacto>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            lista = conn.selectList("Contacto.buscarContactos", nombre);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lista;
    }
}
