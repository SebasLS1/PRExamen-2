package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.PRHormigaDTO;
import Framework.AppException;

public class HormigaDAO extends SQLiteDataHelper implements IDAO<PRHormigaDTO>{

    @Override
    public PRHormigaDTO readBy(Integer id) throws Exception {
        PRHormigaDTO oS = new PRHormigaDTO();
        String query =" SELECT idHormiga, idHormigaTipo, nombre, Estado, Observacion, FechaCrea, FechaModifica "
                     +" FROM    Hormiga   "
                     +" WHERE   Estado ='A' AND idHormiga =   "+ id.toString() ;
        try {
            Connection conn = openConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);  
            while (rs.next()) {
                oS = new PRHormigaDTO(rs.getInt(1), rs.getString(3));
            }
        } 
        catch (SQLException e) {
            throw new AppException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }

    @Override
    public List<PRHormigaDTO> readAll() throws Exception {
        List <PRHormigaDTO> lst = new ArrayList<>();
        String query =" SELECT idHormiga, idHormigaTipo, nombre, Estado, Observacion, FechaCrea, FechaModifica "
                     +" FROM    Hormiga   "
                     +" WHERE   Estado ='A' ";

        try {
            Connection conn = openConnection();         
            Statement  stmt = conn.createStatement();   
            ResultSet rs   = stmt.executeQuery(query);  
            while (rs.next()) {
                PRHormigaDTO s = new PRHormigaDTO(rs.getInt(1), rs.getInt(2), null, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                lst.add(s);
            }
        } 
        catch (SQLException e) {
            throw new AppException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst; 
    }

    @Override
    public boolean create(PRHormigaDTO entity) throws Exception {
        String query = " INSERT INTO Hormiga (idHormigaTipo, nombre, Observacion) VALUES (?, ?, ?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdHormigaClasficacion());
            pstmt.setString(2, entity.getCodigoHormiga());
        
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new AppException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(PRHormigaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Hormiga SET idHormigaTipo = ?, nombre = ?, Observacion = ?, FechaModifica = ? WHERE idHormiga = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdHormigaClasficacion());
            pstmt.setString(2, entity.getCodigoHormiga());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setInt(5, entity.getIdHormiga());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new AppException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE Hormiga SET Estado = ? WHERE idHormiga = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement  pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new AppException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getMaxRow()  throws Exception  {
        String query =" SELECT COUNT(*) TotalReg FROM Hormiga "
                     +" WHERE   Estado ='A' ";
        try {
            Connection conn = openConnection();         
            Statement  stmt = conn.createStatement();   
            ResultSet rs   = stmt.executeQuery(query);  
            while (rs.next()) {
                return rs.getInt(1);                    
            }
        } 
        catch (SQLException e) {
            throw new AppException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
        return 0;
    }
}