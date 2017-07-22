using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class CalificationBranchData
    {
        public static void insert(CalificationBranch caliBranch)
        {
            string[] row = new string[] { "idBranchRestaurant", "idUser", "idTypeDining", "food", "service", "ambience", "message" };
            object[] values = new object[] { caliBranch.idBranchRestaurant, caliBranch.idUser, caliBranch.idTypeDining, caliBranch.food, caliBranch.service, caliBranch.ambience, caliBranch.message };
            string sInsert = QueryHelper.insert("calification_branch", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(CalificationBranch caliBranch)
        {
            string[] row = new string[] { "idBranchRestaurant", "idUser", "idTypeDining", "food", "service", "ambience", "message" };
            object[] values = new object[] { caliBranch.idBranchRestaurant, caliBranch.idUser, caliBranch.idTypeDining, caliBranch.food, caliBranch.service, caliBranch.ambience, caliBranch.message };
            string sUpdate = QueryHelper.update("calification_branch", row, values, "idCalificationBranch", caliBranch.idCalificationBranch);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("calification_branch", "idCalificationBranch", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static CalificationBranch getById(int id)
        {
            string select = "select * from calification_branch where idCalificationBranch=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            CalificationBranch caliBranch;
            if (dt.Rows.Count > 0)
            {
                caliBranch = getByRow(dt.Rows[0]);
                caliBranch.typeDining = TypeDiningCalificationBranchData.getById(caliBranch.idTypeDining);
                caliBranch.photo = PhotoBranchData.getByBranch(caliBranch.idBranchRestaurant);
                return caliBranch;
            }
            return null;
        }

        public static List<CalificationBranch> getAll()
        {
            string select = "select * from calification_branch";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CalificationBranch> list = new List<CalificationBranch>();
            CalificationBranch caliBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    caliBranch = getByRow(row);
                    caliBranch.typeDining = TypeDiningCalificationBranchData.getById(caliBranch.idTypeDining);
                    caliBranch.photo = PhotoBranchData.getByBranch(caliBranch.idBranchRestaurant);
                    list.Add(caliBranch);
                }
                caliBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<CalificationBranch> getAllByUser(int id)
        {
            string select = "select * from calification_branch where idUser=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CalificationBranch> list = new List<CalificationBranch>();
            CalificationBranch caliBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    caliBranch = getByRow(row);
                    caliBranch.typeDining = TypeDiningCalificationBranchData.getById(caliBranch.idTypeDining);
                    caliBranch.photo = PhotoBranchData.getByBranch(caliBranch.idBranchRestaurant);
                    list.Add(caliBranch);
                }
                caliBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<CalificationBranch> getByBranch(int id, int limit, int offset)
        {
            string select = "select * from calification_branch where idBranchRestaurant=" + id.ToString() + " LIMIT " + limit.ToString() + " OFFSET " + offset.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CalificationBranch> list = new List<CalificationBranch>();
            CalificationBranch caliBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    caliBranch = getByRow(row);
                    caliBranch.typeDining = TypeDiningCalificationBranchData.getById(caliBranch.idTypeDining);
                    caliBranch.photo = PhotoBranchData.getByBranch(caliBranch.idBranchRestaurant);
                    list.Add(caliBranch);
                }
                caliBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<CalificationBranch> getAllByBranchTypeDining(int Branch, int typeDining)
        {
            string select = "select * from calification_branch where idBranchRestaurant=" + Branch.ToString() + " and idTypeDining=" + typeDining.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CalificationBranch> list = new List<CalificationBranch>();
            CalificationBranch caliBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    caliBranch = getByRow(row);
                    caliBranch.typeDining = TypeDiningCalificationBranchData.getById(caliBranch.idTypeDining);
                    caliBranch.photo = PhotoBranchData.getByBranch(caliBranch.idBranchRestaurant);
                    list.Add(caliBranch);
                }
                caliBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static CalificationBranch getByRow(DataRow row)
        {
            CalificationBranch caliBranch = new CalificationBranch();
            caliBranch.idCalificationBranch = row.Field<int>("idCalificationBranch");
            caliBranch.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            caliBranch.idUser = row.Field<int>("idUser");
            caliBranch.idTypeDining = row.Field<int>("idTypeDining");
            caliBranch.food = row.Field<int>("food");
            caliBranch.service = row.Field<int>("service");
            caliBranch.ambience = row.Field<int>("ambience");
            caliBranch.averageCalification = row.Field<int>("averageCalification");
            caliBranch.message = row.Field<string>("message");
            caliBranch.date = row.Field<DateTime>("date");
            return caliBranch;
        }
    }
}