using ApiEjemplo.MenuInfo;
using ApiEjemplo.ReservationInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class OrderReservationData
    {
        public static void insert(OrderReservation oRes)
        {
            string sInsert = "Insert into order_reservation (idReservation, idPlateMenu, idUser) values (" + Convert.ToString(oRes.idReservation) + "," + Convert.ToString(oRes.idPlateMenu) + "," + Convert.ToString(oRes.idUser) + ")";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(OrderReservation oRes)
        {
            string sUpdate = "update order_reservation set idReservation=" + Convert.ToString(oRes.idReservation) + ",idPlateMenu=" + Convert.ToString(oRes.idPlateMenu) + ",idUser=" + Convert.ToString(oRes.idUser) + " where idOrderReservation=" + Convert.ToString(oRes.idOrderReservation);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = "delete from order_reservation where idOrderReservation=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static OrderReservation getById(int id)
        {
            string select = "select * from order_reservation where idOrderReservation=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            OrderReservation oRes;
            if (dt.Rows.Count > 0)
            {
                oRes = getByRow(dt.Rows[0]);
                return oRes;
            }
            return null;
        }

        public static List<OrderReservation> getAll()
        {
            string select = "select * from order_reservation";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<OrderReservation> list = new List<OrderReservation>();
            OrderReservation oRes;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oRes = getByRow(row);
                    list.Add(oRes);
                }
                oRes = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<PlateMenu> getAllByReservation(int id)
        {
            string select = "select * from order_reservation where idReservation=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PlateMenu> list = new List<PlateMenu>();
            OrderReservation oRes;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    oRes = getByRow(row);
                    oRes.plate = PlateMenuData.getById(oRes.idPlateMenu);
                    list.Add(oRes.plate);
                }
                oRes = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static OrderReservation getByRow(DataRow row)
        {
            OrderReservation oRes = new OrderReservation();
            oRes.idOrderReservation = row.Field<int>("idOrderReservation");
            oRes.idReservation = row.Field<int>("idReservation");
            oRes.idPlateMenu = row.Field<int>("idPlateMenu");
            oRes.idUser = row.Field<int>("idUser");
            return oRes;
        }
    }
}