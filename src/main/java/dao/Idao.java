package dao;

import java.sql.Connection;

import persistence.DBmanager;

public interface Idao
{
	Connection connection = DBmanager.getConnection();

}
