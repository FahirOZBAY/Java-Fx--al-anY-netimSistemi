/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calisanlarfx;

import Pojo.NewHibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Win
 */
public class Database {

    private static SessionFactory sFact;

    public static void connect() {
        sFact = NewHibernateUtil.getSessionFactory();

    }

    public static void disconnect() {
        sFact.close();

    }

    public static class calisanlar {

        public static ObservableList<Pojo.Calisanlar> getCalisanlars() {
            Session session = sFact.openSession();
            Transaction transaction = session.beginTransaction();
            String key = "Merve";
            ObservableList<Pojo.Calisanlar> calisans = FXCollections.observableArrayList(session.createSQLQuery("Select * from calisanlar").addEntity(Pojo.Calisanlar.class).list());
            transaction.commit();
            session.close();
            return calisans;
        }

        public static void calisanEkle(String ad, String soyad, String dept, String maas) {
            Session session = sFact.openSession();
            Transaction transaction = session.beginTransaction();
            Pojo.Calisanlar c = new Pojo.Calisanlar();
            c.setAd(ad);
            c.setSoyad(soyad);
            c.setDepartman(dept);
            c.setMaas(maas);
            session.save(c);
            transaction.commit();
            session.close();

        }

        public static void calisanEklews(calisanlar c) {
            Session session = sFact.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(c);
            transaction.commit();
            session.close();

        }

        public static void calisanGuncelle(String ad, String soyad, String dept, String maas, int id) {
            Session session = sFact.openSession();
            Transaction transaction = session.beginTransaction();
            Pojo.Calisanlar c = new Pojo.Calisanlar();
            c.setId(id);
            c.setAd(ad);
            c.setSoyad(soyad);
            c.setDepartman(dept);
            c.setMaas(maas);
            session.update(c);
            transaction.commit();
            session.close();

        }

        public static void calisanSil(String ad, String soyad, String dept, String maas, int id) {
            Session session = sFact.openSession();
            Transaction transaction = session.beginTransaction();
            Pojo.Calisanlar c = new Pojo.Calisanlar();
            c.setId(id);
            c.setAd(ad);
            c.setSoyad(soyad);
            c.setDepartman(dept);
            c.setMaas(maas);
            session.delete(c);
            transaction.commit();
            session.close();

        }

        public ObservableList<Pojo.Calisanlar> calisanOku() {
            Session session = sFact.openSession();
            Query q = session.createQuery("From calisanlar");
            ObservableList<Pojo.Calisanlar> ls = (ObservableList<Pojo.Calisanlar>) q.list();
            return ls;

        }

        public ObservableList<Pojo.Calisanlar> calisanBul(String key) {

            Session session = sFact.openSession();
            Transaction transaction = session.beginTransaction();
            ObservableList<Pojo.Calisanlar> calisans = FXCollections.observableArrayList(session.createSQLQuery("Select * from calisanlar where ad Like '%" + key + "%' or soyad LIKE '%" + key + "%' or maas LIKE '%" + key + "%' or departman LIKE '%" + key + "%' ").addEntity(Pojo.Calisanlar.class).list());
            transaction.commit();
            session.close();
            return calisans;

        }

    }
}
