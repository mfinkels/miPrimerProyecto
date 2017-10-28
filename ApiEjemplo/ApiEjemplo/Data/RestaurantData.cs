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
            string sInsert = "Insert into Restaurant (name, description) values ('" + resto.name + "','" +resto.description + "')";
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void Update(Restaurant resto)
        {
            string sUpdate = "update restaurant set name='" + resto.name + "',description='" + resto.description + "' where idRestaurant=" + Convert.ToString(resto.idRestaurant);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static void Delete(int id)
        {
            string sUpdate = "delete from restaurant where idRestaurant=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static Restaurant getById(int id)
        {
            string select = "select * from restaurant where idRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Restaurant resto;
            if (dt.Rows.Count > 0)
            {
                resto = getByRow(dt.Rows[0]);
                resto.socialNetwork = SocialNetworkRestaurantData.getAllByRestaurant(resto.idRestaurant);
                return resto;
            }
            return null;
        }

        public static List<Restaurant> getAll()
        {
            string select = "select * from restaurant";
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
            resto.description = row.Field<string>("description");
            return resto;
        }
    }
}