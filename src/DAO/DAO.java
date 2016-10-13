/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;

public abstract class DAO<Compte> {

    protected Connection connect = null;

    public DAO(Connection conn) {

        this.connect = conn;
    }

    public abstract boolean create(Compte obj);

    public abstract boolean delete(Compte obj);

    public abstract boolean update(Compte obj);

    public abstract Compte find(int id);
}
