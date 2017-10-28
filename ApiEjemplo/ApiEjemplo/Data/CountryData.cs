using ApiEjemplo.AddressInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class CountryData
    {
        public static void insert(Country ct)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { ct.name };
            string sInsert = QueryHelper.insert("country", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(Country ct)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { ct.name };
            string sUpdate = QueryHelper.update("country", row, values, "idCountry", ct.idCountry);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("country", "idCounty", id);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static Country getById(int id)
        {
            string select = "select * from country where idCountry=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Country ct;
            if (dt.Rows.Count > 0)
            {
                ct = getByRow(dt.Rows[0]);
                return ct;
            }
            return null;
        }

        public static List<Country> getAll()
        {
            string select = "select * from country";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Country> list = new List<Country>();
            Country ct;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    ct = getByRow(row);
                    list.Add(ct);
                }
                ct = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static Country getByRow(DataRow row)
        {
            Country ct = new Country();
            ct.idCountry = row.Field<int>("idCountry");
            ct.name = row.Field<string>("name");
            return ct;
        }
    }
}