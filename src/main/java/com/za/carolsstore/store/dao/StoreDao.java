/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.store.dao;


import com.za.carolsstore.config.JDBCConfig;
import com.za.carolsstore.sale.dao.SaleDao;
import com.za.carolsstore.store.model.Review;
import com.za.carolsstore.store.model.Store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicad
 */
public class StoreDao implements iStoreDao {

    private JDBCConfig config = new JDBCConfig();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private int rowsAffected;

    public StoreDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = config.readURL();
        try {
            con = DriverManager.getConnection(url, config.readPropertiesUser(), config.readPropertiesPassword());
        } catch (SQLException ex) {
            Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean addStore(Store store) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Insert Into store(ID, branchName, dailyTarget) values(?,?,?)");
                ps.setString(1, store.getStoreId());
                ps.setString(2, store.getBranchName());
                ps.setFloat(3, store.getDailyTarget());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public Store getStore(String storeID) {
        Store store = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select ID, branchName, dailyTarget From store Where ID = ?");
                ps.setString(1, storeID);
                rs = ps.executeQuery();
                while (rs.next()) { //add
                    store = new Store();
                    store.setStoreId(rs.getString("ID"));
                    store.setBranchName(rs.getString("branchName"));
                    store.setDailyTarget(rs.getFloat("dailyTarget"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return store;
    }

    @Override
    public boolean updateStore(Store store) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Update store Set branchName = ?, dailyTarget = ? Where ID = ?");
                ps.setString(1, store.getBranchName());
                ps.setFloat(2, store.getDailyTarget());
                ps.setString(3, store.getStoreId());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean deleteStore(String storeID) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Delete From store Where ID=?");
                ps.setString(1, storeID);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean addReview(Review review) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Insert Into review(ID, storeID, comment, rating, date, time) values(?,?,?,?,?)");
                ps.setString(1, review.getId());
                ps.setString(2, review.getStoreID());
                ps.setString(3, review.getComment());
                ps.setInt(4, review.getRating());
                ps.setDate(5, review.getDate());
                ps.setTime(6, review.getTime());
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public Review getReview(String reviewID) {
        Review review = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select * from review where ID = ?");
                ps.setString(1, reviewID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    review = new Review();
                    review.setId(rs.getString("ID"));
                    review.setStoreID(rs.getString("storeID"));
                    review.setComment(rs.getString("comment"));
                    review.setRating(rs.getInt("rating"));
                    review.setDate(rs.getDate("date"));
                    review.setTime(rs.getTime("time"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return review;
    }

    @Override
    public List<Review> getReviewsByStore(String storeID) {
        List<Review> reviews = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select * From review Where storeID = ?");
                ps.setString(1, storeID);
                rs = ps.executeQuery();
                reviews = new ArrayList<>();
                while (rs.next()) {
                    Review review = new Review();
                    review.setId(rs.getString("ID"));
                    review.setStoreID(rs.getString("storeID"));
                    review.setComment(rs.getString("comment"));
                    review.setRating(rs.getInt("rating"));
                    review.setDate(rs.getDate("date"));
                    review.setTime(rs.getTime("time"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return reviews;
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = null;
        if (con != null) {
            try {
                ps = con.prepareStatement("Select * From review");
                rs = ps.executeQuery();
                reviews = new ArrayList<>();
                while (rs.next()) {
                    Review review = new Review();
                    review.setId(rs.getString("ID"));
                    review.setStoreID(rs.getString("storeID"));
                    review.setComment(rs.getString("comment"));
                    review.setRating(rs.getInt("rating"));
                    review.setDate(rs.getDate("date"));
                    review.setTime(rs.getTime("time"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return reviews;
    }

    @Override
    public boolean deleteReview(String reviewID) {
        if (con != null) {
            try {
                ps = con.prepareStatement("Delete From review Where ID=?");
                ps.setString(1, reviewID);
                rowsAffected = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StoreDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return rowsAffected == 1;
    }
}