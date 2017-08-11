using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class ReservationData
    {
        public static void insert(Reservation res)
        {
            string[] row = new string[] { "idUser", "idBranchRestaurant", "date", "guest"};
            object[] values = new object[] { res.idUser, res.idBranchRestaurant, res.date.ToString("yyyy-MM-dd HH':'mm':'ss"), res.guest };
            string sInsert = QueryHelper.insert("reservation", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(Reservation res)
        {
            string[] row = new string[] { "idUser", "idBranchRestaurant", "date", "guest" };
            object[] values = new object[] { res.idUser, res.idBranchRestaurant, res.date.ToString("yyyy-MM-dd HH':'mm':'ss"), res.guest };
            string sUpdate = QueryHelper.update("reservation", row, values, "idReservation", res.idReservation);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = "delete from reservation where idReservation=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static Reservation getById(int id)
        {
            string select = "select * from reservation where idReservation=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Reservation res;
            if (dt.Rows.Count > 0)
            {
                res = getByRow(dt.Rows[0]);
                return res;
            }
            return null;
        }

        public static List<Reservation> getByIdUser(int id, string simbol)
        {
            string select = "select * from reservation where idUser=" + id.ToString() + " AND date " + simbol + " CURDATE()";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Reservation> list = new List<Reservation>();
            Reservation res;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    res = getByRow(row);
                    res.branch = BranchRestaurantData.getById(res.idBranchRestaurant);
                    res.restaurant = RestaurantData.getById(res.branch.idRestaurant);
                    list.Add(res);
                }
                res = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<Reservation> getAll()
        {
            string select = "select * from reservation";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Reservation> list = new List<Reservation>();
            Reservation res;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    res = getByRow(row);
                    list.Add(res);
                }
                res = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static Reservation getByRow(DataRow row)
        {
            Reservation res = new Reservation();
            res.idReservation = row.Field<int>("idReservation");
            res.idUser = row.Field<int>("idUser");
            res.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            res.date = row.Field<DateTime>("date");
            res.guest = row.Field<int>("guest");
            return res;
        }
    }
}