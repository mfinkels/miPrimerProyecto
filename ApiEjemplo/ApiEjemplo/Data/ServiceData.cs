using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class ServiceData
    {
        public static void insert(Service s)
        {
            string sInsert = "Insert into service (name) values ('" + s.name + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(Service s)
        {
            string sUpdate = "update service set name='" + s.name + "' where idService=" + Convert.ToString(s.idService);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from service where idService=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static Service getById(int id)
        {
            string select = "select * from service where idService=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Service s;
            if (dt.Rows.Count > 0)
            {
                s = getByRow(dt.Rows[0]);
                return s;
            }
            return null;
        }

        public static List<Service> getAll()
        {
            string select = "select * from service";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Service> list = new List<Service>();
            Service s;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    s = getByRow(row);
                    list.Add(s);
                }
                s = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static Service getByRow(DataRow row)
        {
            Service s = new Service();
            s.idService = row.Field<int>("idService");
            s.name = row.Field<string>("name");
            return s;
        }
    }
}