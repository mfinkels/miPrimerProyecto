using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class CuisineData
    {
        public static void insert(Cuisine cuisine)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { cuisine.name };
            string sInsert = QueryHelper.insert("cuisine", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(Cuisine cuisine)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { cuisine.name };
            string sUpdate = QueryHelper.update("cuisine", row, values, "idCousine", cuisine.idCousine);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("cuisine", "idCousine", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static Cuisine getById(int id)
        {
            string select = "select * from cuisine where idCuisine=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Cuisine cuisine;
            if (dt.Rows.Count > 0)
            {
                cuisine = getByRow(dt.Rows[0]);
                return cuisine;
            }
            return null;
        }

        public static List<Cuisine> getAll()
        {
            string select = "select * from cuisine";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Cuisine> list = new List<Cuisine>();
            Cuisine cuisine;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    cuisine = getByRow(row);
                    list.Add(cuisine);
                }
                cuisine = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static Cuisine getByRow(DataRow row)
        {
            Cuisine cuisine = new Cuisine();
            cuisine.idCousine = row.Field<int>("idCuisine");
            cuisine.name = row.Field<string>("name");
            return cuisine;
        }
    }
}