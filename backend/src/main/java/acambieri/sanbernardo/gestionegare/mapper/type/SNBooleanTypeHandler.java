package acambieri.sanbernardo.gestionegare.mapper.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SNBooleanTypeHandler implements TypeHandler {

	public void setParameter( PreparedStatement ps, int i, Object parameter, JdbcType jdbcType ) throws SQLException{
		ps.setString( i, jdbcEncode( (Boolean)parameter ) );
	}

	public Object getResult ( ResultSet rs, int columnIndex ) throws SQLException {
		return jdbcDecode( rs.getString( columnIndex ) );
	}

	public Object getResult( ResultSet rs, String columnName ) throws SQLException{
		return jdbcDecode( rs.getString( columnName ) );
	}

	public Object getResult( CallableStatement cs, int columnIndex ) throws SQLException{
		return jdbcDecode( cs.getString( columnIndex ) );
	}

	public static String jdbcEncode( boolean value ){
		return value ? "S" : "N";
	}

	public static boolean jdbcDecode( String value ){
		return value != null && !"N".equals( value.trim() );
	}
}
