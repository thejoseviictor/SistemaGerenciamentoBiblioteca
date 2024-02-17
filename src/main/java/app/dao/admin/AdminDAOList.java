package app.dao.admin;

import app.dao.DatFilesFolder;

import app.model.Admin;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * <b>Esta classe implementa os métodos CRUD para administradores</b>
 * Os métodos de armazenamento atuais são os arquivos binários ".dat"
 *
 * @author José Victor Oliveira
 * @author Micael Muniz
 * @see app.model.Admin
 * @see app.dao.admin.AdminDAO
 */
public class AdminDAOList implements AdminDAO {
    private List<Admin> admins;
    private Integer lastId = 0;
    private String filePath = System.getProperty("user.dir") + "\\files\\Admins.dat";

    public AdminDAOList() {
        this.admins = new LinkedList<Admin>();
    }

    /**
     * Método que carrega os Admins do arquivo binário "Admins.dat" para a lista "listaAdmins".
     */
    @Override
    public void loadDatFile() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filePath);
        ObjectInputStream object = new ObjectInputStream(file);
        admins = (LinkedList<Admin>) object.readObject();
    }

    /**
     * Método que salva os Admins da lista "listaAdmins" para o arquivo binário "Admins.dat".
     */
    @Override
    public void saveDatFile() throws IOException {
        DatFilesFolder folder = new DatFilesFolder();
        folder.ensureDestinationFolderExists();
        FileOutputStream file = new FileOutputStream(filePath);
        ObjectOutputStream object = new ObjectOutputStream(file);
        object.writeObject(admins);
    }

    /**
     * Método que cria um novo administrador
     *
     * @param model model do administrador
     * @return model do administrador
     */
    @Override
    public Admin create(Admin model) {
        // Vai verificar se o model já existe na lista.
        if (!admins.contains(model)) {
            lastId++;
            model.setId(lastId);
            admins.add(model);
        }
        return model;
    }

    /**
     * Método que retorna todos os administradores
     *
     * @return lista de administradores
     */
    @Override
    public List<Admin> getAll() {
        return admins;
    }

    /**
     * Método que retorna um administrador específico
     *
     * @param id model do administrador
     * @return retorna um administrador específico
     */
    @Override
    public Admin findById(Integer id) {
        for (Admin admin : admins) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }
        return null;
    }

    /**
     * Método que atualiza os atributos de um administrador específico
     *
     * @param model model do administrador
     * @return model do administrador
     */
    @Override
    public Admin update(Admin model) {
        if (admins.contains(model)) {
            admins.set(admins.indexOf(model), model);
        }
        return model;
    }

    /**
     * Método que remove um administrador específico
     *
     * @param model model do administrador
     */
    @Override
    public void delete(Admin model) {
        admins.remove(model);
    }

    /**
     * Método que remove todos os administradores
     */
    @Override
    public void deleteAll() {
        admins.clear();
    }
}
