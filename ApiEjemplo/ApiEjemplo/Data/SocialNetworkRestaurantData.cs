using ApiEjemplo.RestaurantInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class SocialNetworkRestaurantData
    {
        public static void insert(SocialNetworkRestaurant snResto)
        {
            string sInsert = "Insert into social_network_restaurant (idRestaurant, idTypeSocialNetwork, value) values (" + Convert.ToString(snResto.idRestaurant) + "," + Convert.ToString(snResto.idTypeSocialNetwork) + "," + Convert.ToString(snResto.value) + ")";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(SocialNetworkRestaurant snResto)
        {
            string sUpdate = "update social_network_restaurant set idRestaurant=" + Convert.ToString(snResto.idRestaurant) + ",idTypeSocialNetwork=" + Convert.ToString(snResto.idTypeSocialNetwork) + ",value=" + Convert.ToString(snResto.value) + " where idSocialNetworkRestaurant=" + Convert.ToString(snResto.idSocialNetworkRestaurant);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = "delete from social_network_restaurant where idSocialNetworkRestaurant=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static SocialNetworkRestaurant getById(int id)
        {
            string select = "select * from social_network_restaurant where idSocialNetworkRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            SocialNetworkRestaurant snResto;
            if (dt.Rows.Count > 0)
            {
                snResto = getByRow(dt.Rows[0]);
                snResto.type = TypeSocialNetworkData.getById(snResto.idTypeSocialNetwork);
                return snResto;
            }
            return null;
        }

        public static SocialNetworkRestaurant getByRestaurant(int id)
        {
            string select = "select * from social_network_restaurant where idRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            SocialNetworkRestaurant snResto;
            if (dt.Rows.Count > 0)
            {
                snResto = getByRow(dt.Rows[0]);
                snResto.type = TypeSocialNetworkData.getById(snResto.idTypeSocialNetwork);
                return snResto;
            }
            return null;
        }

        public static List<SocialNetworkRestaurant> getAll()
        {
            string select = "select * from social_network_restaurant";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<SocialNetworkRestaurant> list = new List<SocialNetworkRestaurant>();
            SocialNetworkRestaurant snResto;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    snResto = getByRow(row);
                    snResto.type = TypeSocialNetworkData.getById(snResto.idTypeSocialNetwork);
                    list.Add(snResto);
                }
                snResto = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<SocialNetworkRestaurant> getAllByRestaurant(int id)
        {
            string select = "select * from social_network_restaurant where idRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<SocialNetworkRestaurant> list = new List<SocialNetworkRestaurant>();
            SocialNetworkRestaurant snResto;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    snResto = getByRow(row);
                    snResto.type = TypeSocialNetworkData.getById(snResto.idTypeSocialNetwork);
                    list.Add(snResto);
                }
                snResto = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static SocialNetworkRestaurant getByRow(DataRow row)
        {
            SocialNetworkRestaurant snResto = new SocialNetworkRestaurant();
            snResto.idSocialNetworkRestaurant = row.Field<int>("idSocialNetworkRestaurant");
            snResto.idRestaurant = row.Field<int>("idRestaurant");
            snResto.idTypeSocialNetwork = row.Field<int>("idTypeSocialNetwork");
            snResto.value = row.Field<string>("value");
            return snResto;
        }
    }
}