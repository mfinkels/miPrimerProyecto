using ApiEjemplo.AddressInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class DayData
    {
        public static void insert(Day d)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { d.name };
            string sInsert = QueryHelper.insert("day", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(Day d)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { d.name };
            string sUpdate = QueryHelper.update("day", row, values, "idDay", d.idDay);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("day", "idDay", id);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static Day getById(int id)
        {
            string select = "select * from day where idDay=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Day d;
            if (dt.Rows.Count > 0)
            {
                d = getByRow(dt.Rows[0]);
                return d;
            }
            return null;
        }

        public static List<Day> getAll()
        {
            string select = "select * from day";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Day> list = new List<Day>();
            Day d;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    d = getByRow(row);
                    list.Add(d);
                }
                d = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static Day getByRow(DataRow row)
        {
            Day d = new Day();
            d.idDay = row.Field<int>("idDay");
            d.name = row.Field<string>("name");
            return d;
        }
    }
}