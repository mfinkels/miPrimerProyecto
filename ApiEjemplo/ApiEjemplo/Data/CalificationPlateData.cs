using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class CalificationPlateData
    {

        public static void insert(CalificationPlate caliPlate)
        {
            string[] row = new string[] { "idBranchRestaurant", "idUser", "idPlateMenu", "value", "message" };
            object[] values = new object[] { caliPlate.idBranchRestaurant, caliPlate.idUser, caliPlate.idPlateMenu, caliPlate.value, caliPlate.message };
            string sInsert = QueryHelper.insert("calification_plate", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(CalificationPlate caliPlate)
        {
            string[] row = new string[] { "idBranchRestaurant", "idUser", "idPlateMenu", "value", "message" };
            object[] values = new object[] { caliPlate.idBranchRestaurant, caliPlate.idUser, caliPlate.idPlateMenu, caliPlate.value, caliPlate.message };
            string sUpdate = QueryHelper.update("calification_plate", row, values, "idCalificationPlate", caliPlate.idCalificationPlate);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("calification_plate", "idCalificationPlate", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static CalificationPlate getById(int id)
        {
            string select = "select * from calification_plate where idCalificationPlate=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            CalificationPlate caliPlate;
            if (dt.Rows.Count > 0)
            {
                caliPlate = getByRow(dt.Rows[0]);
                return caliPlate;
            }
            return null;
        }

        public static List<CalificationPlate> getAll()
        {
            string select = "select * from calification_plate";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CalificationPlate> list = new List<CalificationPlate>();
            CalificationPlate caliPlate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    caliPlate = getByRow(row);
                    list.Add(caliPlate);
                }
                caliPlate = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<CalificationPlate> getAllByUser(int id)
        {
            string select = "select * from calification_plate where idUser=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CalificationPlate> list = new List<CalificationPlate>();
            CalificationPlate caliPlate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    caliPlate = getByRow(row);
                    list.Add(caliPlate);
                }
                caliPlate = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<CalificationPlate> getAllByPlate(int id)
        {
            string select = "select * from calification_plate where idCalificationPlate=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CalificationPlate> list = new List<CalificationPlate>();
            CalificationPlate caliPlate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    caliPlate = getByRow(row);
                    list.Add(caliPlate);
                }
                caliPlate = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static CalificationPlate getByRow(DataRow row)
        {
            CalificationPlate caliPlate = new CalificationPlate();
            caliPlate.idCalificationPlate = row.Field<int>("idCalificationPlate");
            caliPlate.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            caliPlate.idUser = row.Field<int>("idUser");
            caliPlate.idPlateMenu = row.Field<int>("idPlateMenu");
            caliPlate.value = row.Field<int>("value");
            caliPlate.message = row.Field<string>("message");
            return caliPlate;
        }
    }
}