using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class RestaurantData
    {
        public static void Insert(Restaurant resto)
        {
            string sInsert = "Insert into Restaurant (name, information, photo, rangeOfPriceMin, rangeOfPriceMax, promotion, service, menu, calificationRestaurant, address, reservation) values ('" + resto.name + "','" +resto.information + "','" + resto.photo + "'," + Convert.ToString(resto.rangeOfPriceMin) + "," + Convert.ToString(resto.rangeOfPriceMin) + "," + Convert.ToString(resto.promotion) + ",'" + resto.service + "'," + Convert.ToString(resto.menu) + "," + Convert.ToString(resto.calificationRestaurant) + ",'" + resto.address + "'," + Convert.ToString(resto.reservation) + ")";
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void Update(Restaurant resto)
        {
            string sUpdate = "update Restaurant set name='" + resto.name + "',information='" + resto.information + "',photo='" + resto.photo + "',rangeOfPriceMin=" + Convert.ToString(resto.rangeOfPriceMin)+ ",rangeofPriceMax=" + Convert.ToString(resto.rangeOfPriceMax) + "promotion=" + Convert.ToString(resto.promotion)+ ",service='" + resto.service + "',menu=" + Convert.ToString(resto.menu) + ",calificationRestaurant=" + Convert.ToString(resto.calificationRestaurant) + ",address='" + resto.address + "',reservation=" + Convert.ToString(resto.reservation) + " where idRestaurant=" + Convert.ToString(resto.idRestaurant);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static void Delete(int id)
        {
            string sUpdate = "delete from personas where idRestaurant=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static Restaurant getById(int id)
        {
            string select = "select * from Restaurant where idRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Restaurant resto;
            if (dt.Rows.Count > 0)
            {
                resto = getByRow(dt.Rows[0]);
                return resto;
            }
            return null;
        }

        public static List<Restaurant> getAll()
        {
            string select = "select * from Restaurant";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Restaurant> list = new List<Restaurant>();
            Restaurant resto;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    resto = getByRow(row);
                    list.Add(resto);
                }
                resto = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static Restaurant getByRow(DataRow row)
        {
            Restaurant resto = new Restaurant();
            resto.idRestaurant = row.Field<int>("idRestaurant");
            resto.name = row.Field<string>("name");
            resto.information = row.Field<string>("information");
            resto.photo = row.Field<string>("photo");
            resto.rangeOfPriceMin = row.Field<int>("rangeOfPriceMin");
            resto.rangeOfPriceMax = row.Field<int>("rangeOfPriceMax");
            resto.promotion = row.Field<int>("promotion");
            resto.service = row.Field<string>("service");
            resto.menu = row.Field<int>("menu");
            resto.calificationRestaurant = row.Field<int>("calificationRestaurant");
            resto.address = row.Field<string>("address");
            resto.reservation = row.Field<int>("reservation");
            return resto;
        }
    }
}