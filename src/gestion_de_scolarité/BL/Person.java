/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.BL;

import java.util.Date;
import javax.swing.Icon;

/**
 *
 * @author slimane
 */
public class Person {
    
    private int idPerson;
    private String nom;
    private String prenom;
    private String address;
    private Date dateDeNaissance;
    private String lieuDeNaissance;
    private String email;
    private String phone;
    private boolean sex;
    private Icon photos;
    private byte[] imgByte;

    public Person() {
    }

    public Person(int idPerson, String nom, String prenom, String address, Date dateDeNaissance, String lieuDeNaissance, String email, String phone, boolean sex) {
        this.idPerson = idPerson;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
    }

    public Person(int idPerson, String nom, String prenom, String address, Date dateDeNaissance, String lieuDeNaissance, String email, String phone, boolean sex, byte[] imgByte) {
        this.idPerson = idPerson;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.imgByte = imgByte;
    }
    
    

    
    public Person(int idPerson, String nom, String prenom, String address, Date dateDeNaissance, String lieuDeNaissance, String email, String phone, boolean sex, Icon photos) {
        this.idPerson = idPerson;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.photos = photos;
    }

    public Icon getPhotos() {
        return photos;
    }

    public void setPhotos(Icon photos) {
        this.photos = photos;
    }

    
    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setImgByte(byte[] imgByte) {
        this.imgByte = imgByte;
    }
    
    
    
    
}
