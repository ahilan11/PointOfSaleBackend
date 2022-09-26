/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.product.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.za.carolsstore.product.model.Category;
import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.config.JDBCConfig;
import com.za.carolsstore.store.model.Store;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicad
 */
public class ProductDaoImp implements iProductDao {

    private JDBCConfig config = new JDBCConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private int rowsAffected;
    private ObjectMapper om;

    public ProductDaoImp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = config.readURL();
        try {
            con = DriverManager.getConnection(url, config.readPropertiesUser(), config.readPropertiesPassword());
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Create--------------------------------------------------------------------
    @Override
    public boolean addProduct(Product p) {
        try {
            ps = con.prepareStatement("insert into product(ID, name, description, size, price) values(?,?,?,?,?);");
            ps.setString(1, p.getId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getSize());
            ps.setDouble(5, p.getPrice());
            rowsAffected = ps.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public  List<Store> getStoresByProduct(String productID){
        List<Store> stores = new ArrayList<>();
        try {
            ps = con.prepareStatement("Select store.ID as storeIDs, name, description, size, price From product Inner Join (inventory inner join store on inventory.storeID=store.ID) On product.ID = inventory.productID Where product.ID = ?");
            ps.setString(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Store p = new Store();
                p.setStoreId(rs.getString("storeIDs"));
             
                stores.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return stores;
    }
    @Override
    public boolean addKeepAside(KeepAside keepAside) {
        if(con!=null){
            try {
                ps = con.prepareStatement("Insert Into keepaside(ID, productID, customerEmail, dateCreated, timeCreated) Values (?,?,?,?,?)");
                ps.setNString(1, keepAside.getKeepAsideID());
                ps.setString(2, keepAside.getProductID());
                ps.setString(3, keepAside.getCustomerEmail());
//                ps.setDate(4, keepAside.getDateCreated());
//                ps.setTime(5, keepAside.getTimeCreated());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean addIBT(InterBranchTransfer t) {
        try {
            ps = con.prepareStatement("insert into ibtitem(ID, productID, toStoreID, fromStoreID, "
                    + "approved, received, customerPhoneNumber, dateApproved, timeApproved, dateRequested, "
                    + "timeRequested) values(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, t.getIbtID());
            ps.setString(2, t.getProduct().getId()); //necessary only if ids not kept
            ps.setString(3, t.getToStore().getStoreId());
            ps.setString(4, t.getFromStore().getStoreId());
            ps.setBoolean(5, t.isApproved());
            ps.setBoolean(6, t.isReceived());
            ps.setString(7, t.getCustomerPhoneNumber());
//            ps.setDate(8, t.getDateApproved());
//            ps.setTime(9, t.getTimeApproved());
//            ps.setDate(10, t.getDateRequested());
//            ps.setTime(11, t.getTimeRequested());
            rowsAffected = ps.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public boolean addCategory(Category category) {
        if(con!=null){
            try {
                ps = con.prepareStatement("Insert Into category(ID, name) values (?,?)");
                ps.setString(1, category.getCategoryID());
                ps.setString(2, category.getCategoryName());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return rowsAffected == 1;
    }

    //Read----------------------------------------------------------------------
    @Override
    public Product getProduct(String productID) {
        Product product = null;
        try {
            ps = con.prepareStatement("Select name, description, size, price, onSale, onSalePrice From product where ID = ?");
            ps.setString(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(productID);
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setSize(rs.getString("size"));
                product.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return product;
    }

    @Override
    public List<Product> getProductsByStore(String storeID) {
        List<Product> products = new ArrayList<>();
        try {
            ps = con.prepareStatement("Select ID, name, description, size, price "
                    + "From product Inner Join inventory On product.ID = inventory.productID "
                    + "Where storeID = ?");
            ps.setString(1, storeID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("ID"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setSize(rs.getString("size"));
                p.setPrice(rs.getDouble("price"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryID) {
        List<Product> products = new ArrayList<>();
        try {
            ps = con.prepareStatement("Select product.ID, product.name, product.description, product.size, product.price "
                    + "From product Inner Join product_category On product.ID = product_category.productID "
                    + "Where categoryID = ?");
            ps.setString(1, categoryID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("ID"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setSize(rs.getString("size"));
                p.setPrice(rs.getDouble("price"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            ps = con.prepareStatement("Select ID, name, description, size, price "
                    + "From product");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("ID"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setSize(rs.getString("size"));
                p.setPrice(rs.getDouble("price"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return products;
    }
    
    @Override
    public KeepAside getKeepAside(String keepAsideID) {
        KeepAside ka = null;
        if(con!=null){
            try {
                ps = con.prepareStatement("Select customerEmail, storeID, productID, dateCreated, timeCreated From keepaside Where ID = ?");
                ps.setString(1, keepAsideID);
                rs = ps.executeQuery();
                while(rs.next()){
                    ka = new KeepAside();
                    ka.setKeepAsideID(keepAsideID);
                    ka.setCustomerEmail(rs.getString("customerEmail"));
                    ka.setStoreID(rs.getString("storeID"));
                    ka.setProductID(rs.getString("productID"));
//                    ka.setDateCreated(rs.getDate("dateCreated"));
//                    ka.setTimeCreated(rs.getTime("timeCreated"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return ka;
    }
    
    @Override
    public List<KeepAside> getKeepAsidesByStore(String storeID) {
        List<KeepAside> keepAsides = new ArrayList<>();
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID, customerEmail, productID, dateCreated, timeCreated From keepaside Where storeID = ?");
                ps.setString(1, storeID);
                rs = ps.executeQuery();
                while(rs.next()){
                    KeepAside ka = new KeepAside();
                    ka.setKeepAsideID(rs.getString("ID"));
                    ka.setCustomerEmail(rs.getString("customerEmail"));
                    ka.setStoreID(storeID);
                    ka.setProductID(rs.getString("productID"));
//                    ka.setDateCreated(rs.getDate("dateCreated"));
//                    ka.setTimeCreated(rs.getTime("timeCreated"));
                    keepAsides.add(ka);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return keepAsides;
    }

    @Override
    public List<KeepAside> getAllKeepAsides() {
        List<KeepAside> keepAsides = new ArrayList<>();
        if(con!=null){
            try {
                ps = con.prepareStatement("Select ID, customerEmail, storeID, productID, dateCreated, timeCreated From keepaside");
                rs = ps.executeQuery();
                while(rs.next()){
                    KeepAside ka = new KeepAside();
                    ka.setKeepAsideID(rs.getString("ID"));
                    ka.setCustomerEmail(rs.getString("customerEmail"));
                    ka.setStoreID(rs.getString("storeID"));
                    ka.setProductID(rs.getString("productID"));
//                    ka.setDateCreated(rs.getDate("dateCreated"));
//                    ka.setTimeCreated(rs.getTime("timeCreated"));
                    keepAsides.add(ka);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return keepAsides;
    }
    
    @Override
    public InterBranchTransfer getIbt(String transferID) {
        InterBranchTransfer transfer = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, productID, toStoreID, fromStoreID, "
                    + "approved, received, customerPhoneNumber, dateApproved, timeApproved, dateRequested, "
                    + "timeRequested from ibtitem Where ID = ?");
                ps.setString(1, transferID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    transfer = new InterBranchTransfer();
                    transfer.setIbtID(transferID);
                    transfer.setProductID(rs.getString("productID"));
                    transfer.setToStoreID(rs.getString("toStoreID"));
                    transfer.setFromStoreID(rs.getString("fromStoreID"));
                    transfer.setApproved(rs.getBoolean("approved"));
                    transfer.setReceived(rs.getBoolean("received"));
                    transfer.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
//                    transfer.setDateApproved(rs.getDate("dateApproved"));
//                    transfer.setTimeApproved(rs.getTime("timeApproved"));
//                    transfer.setDateRequested(rs.getDate("dateRequested"));
//                    transfer.setTimeRequested(rs.getTime("timeRequested"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return transfer;
    }

    @Override
    public List<InterBranchTransfer> getIbtsByStore(String storeID) {
        List<InterBranchTransfer> transfers = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, productID, toStoreID, fromStoreID, "
                    + "approved, received, customerPhoneNumber, dateApproved, timeApproved, dateRequested, "
                    + "timeRequested from ibtitem where toStoreID = ?");
                ps.setString(1, storeID);
                rs = ps.executeQuery();
                while (rs.next()) {          
                    InterBranchTransfer transfer = new InterBranchTransfer();
                    transfer.setIbtID(rs.getString("ID"));
                    transfer.setProductID(rs.getString("productID"));
                    transfer.setToStoreID(rs.getString("toStoreID"));
                    transfer.setFromStoreID(rs.getString("fromStoreID"));
                    transfer.setApproved(rs.getBoolean("approved"));
                    transfer.setReceived(rs.getBoolean("received"));
                    transfer.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
//                    transfer.setDateApproved(rs.getDate("dateApproved"));
//                    transfer.setTimeApproved(rs.getTime("timeApproved"));
//                    transfer.setDateRequested(rs.getDate("dateRequested"));
//                    transfer.setTimeRequested(rs.getTime("timeRequested"));
                    transfers.add(transfer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return transfers;
    }

    @Override
    public List<InterBranchTransfer> getAllIbts() {
        List<InterBranchTransfer> transfers = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, productID, toStoreID, fromStoreID, "
                    + "approved, received, customerPhoneNumber, dateApproved, timeApproved, dateRequested, "
                    + "timeRequested from ibtitem");
                rs = ps.executeQuery();
                while (rs.next()) {          
                    InterBranchTransfer transfer = new InterBranchTransfer();
                    transfer.setIbtID(rs.getString("ID"));
                    transfer.setProductID(rs.getString("productID"));
                    transfer.setToStoreID(rs.getString("toStoreID"));
                    transfer.setFromStoreID(rs.getString("fromStoreID"));
                    transfer.setApproved(rs.getBoolean("approved"));
                    transfer.setReceived(rs.getBoolean("received"));
                    transfer.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
//                    transfer.setDateApproved(rs.getDate("dateApproved"));
//                    transfer.setTimeApproved(rs.getTime("timeApproved"));
//                    transfer.setDateRequested(rs.getDate("dateRequested"));
//                    transfer.setTimeRequested(rs.getTime("timeRequested"));
                    transfers.add(transfer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return transfers;
    }
    
    @Override
    public Category getCategory(String categoryID) {
        Category category = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, name from category Where ID = ?");
                ps.setString(1, categoryID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    category = new Category(
                            rs.getString("ID"),
                            rs.getString("name")
                    );
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);                    }
                }
            }
        }
        return category;
    }
    
    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, name From category");
                rs = ps.executeQuery();
                while (rs.next()) {
                    categoryList.add(new Category(
                            rs.getString("ID"),
                            rs.getString("name")
                    ));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return categoryList;
    }

    //Update--------------------------------------------------------------------   
    @Override
    public boolean updateProduct(Product product) {
        try {
            ps = con.prepareStatement("update product set name = ?, description=?, size=?, price = ? where ID = ?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getSize());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getId());
            rowsAffected = ps.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public boolean setProductOnSale(Product p, double onSale) {
        try {
            ps = con.prepareStatement("update product set onsalePrice = ? where ID = ?");
            ps.setDouble(1, onSale);
            ps.setString(2, p.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }
        return false;
    }
    
    @Override
    public boolean updateKeepAside(KeepAside keepAside){
        if(con!=null){
            try {
                ps = con.prepareStatement("Update keepAside Set storeID = ?,"
                        + "productID = ?, customerEmail = ?, dateCreated = ?, "
                        + "timeCreated = ? Where ID = ?");
                ps.setString(1, keepAside.getStoreID());
                ps.setString(2, keepAside.getProductID());
                ps.setString(3, keepAside.getCustomerEmail());
//                ps.setDate(4, keepAside.getDateCreated());
//                ps.setTime(5, keepAside.getTimeCreated());
//                ps.setString(6, keepAside.getKeepAsideID());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean updateIbt(InterBranchTransfer transfer){
        try {
            ps = con.prepareStatement("Update ibtitem Set ID = ?, productID = ?, toStoreID = ?, fromStoreID = ?, "
                    + "approved = ?, received = ?, customerPhoneNumber = ?, dateApproved = ?, timeApproved = ?, dateRequested = ?, "
                    + "timeRequested = ? where ID = ?");
            ps.setString(1, transfer.getIbtID());
            ps.setString(2, transfer.getProduct().getId()); //necessary only if ids not kept
            ps.setString(3, transfer.getToStore().getStoreId());
            ps.setString(4, transfer.getFromStore().getStoreId());
            ps.setBoolean(5, transfer.isApproved());
            ps.setBoolean(6, transfer.isReceived());
            ps.setString(7, transfer.getCustomerPhoneNumber());
            ps.setDate(8, Date.valueOf(transfer.getDateApproved().toString()));
            ps.setTime(9, Time.valueOf(transfer.getTimeApproved().toString()));
            ps.setDate(10, Date.valueOf(transfer.getDateRequested().toString()));
            ps.setTime(11, Time.valueOf(transfer.getTimeRequested().toString()));
            rowsAffected = ps.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public boolean updateCategory(Category category, String categoryID){
        if (con != null) {
            try {
                ps = con.prepareStatement("Update category set ID = ?, name = ? where ID = ?");
                ps.setString(1, category.getCategoryID());
                ps.setString(2, category.getCategoryName());
                ps.setString(3, categoryID);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return rowsAffected == 1;
    }

    //Delete--------------------------------------------------------------------
    @Override
    public boolean deleteProduct(String productID) {
        try {
            ps = con.prepareStatement("Delete From product Where ID = ?");
            ps.setString(1, productID);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
    
    @Override
    public boolean deleteKeepAside(String keepAsideID) {
        if(con!=null){
            try {
                ps = con.prepareStatement("Delete From keepaside Where ID = ?");
                ps.setString(1, keepAsideID);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return rowsAffected == 1;
    }   
    
    @Override
    public boolean deleteIbt(String transferID){
        try {
            ps = con.prepareStatement("Delete From ibtitem Where ID = ?");
            ps.setString(1, transferID);
            rowsAffected = ps.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);                }
            }
        }
    }
    
    @Override
    public boolean deleteCategory(String categoryID) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Delete from category where ID = ?");
                ps.setString(1, categoryID);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return rowsAffected == 1;
    } 
}
