using ApiEjemplo.AddressInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class CityData
    {
        public static void insert(City c)
        {
            string[] row = new string[] { "idState", "name" };
            object[] values = new object[] { c.idState, c.name };
            string sInsert = QueryHelper.insert("city", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(City c)
        {
            string[] row = new string[] { "idState", "name" };
            object[] values = new object[] { c.idState, c.name };
            string sUpdate = QueryHelper.update("city", row, values, "idCity", c.idCity);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("city", "idCity", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static City getById(int id)
        {
            string select = "select * from city where idCity=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            City c;
            if (dt.Rows.Count > 0)
            {
                c = getByRow(dt.Rows[0]);
                return c;
            }
            return null;
        }

        public static List<City> getAll()
        {
            string select = "select * from city";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<City> list = new List<City>();
            City c;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    c = getByRow(row);
                    list.Add(c);
                }
                c = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static City getByRow(DataRow row)
        {
            City c = new City();
            c.idCity = row.Field<int>("idCity");
            c.idState = row.Field<int>("idState");
            c.name = row.Field<string>("name");
            return c;
        }
    }
}