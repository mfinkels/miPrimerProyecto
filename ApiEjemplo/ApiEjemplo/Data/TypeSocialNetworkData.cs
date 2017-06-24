using ApiEjemplo.RestaurantInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class TypeSocialNetworkData
    {
        public static void insert(TypeSocialNetwork tSocial)
        {
            string sInsert = "Insert into type_social_network (name, icon) values ('" + tSocial.name + "','" + tSocial.icon + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(TypeSocialNetwork tSocial)
        {
            string sUpdate = "update type_social_network set name='" + tSocial.name + "',icon='" + tSocial.icon + "' where idTypeSocialNetwork=" + Convert.ToString(tSocial.idTypeSocialNetwork);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from type_social_network where idTypeSocialNetwork=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static TypeSocialNetwork getById(int id)
        {
            string select = "select * from type_social_network where idTypeSocialNetwork=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            TypeSocialNetwork tSocial;
            if (dt.Rows.Count > 0)
            {
                tSocial = getByRow(dt.Rows[0]);
                return tSocial;
            }
            return null;
        }

        public static List<TypeSocialNetwork> getAll()
        {
            string select = "select * from type_social_network";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TypeSocialNetwork> list = new List<TypeSocialNetwork>();
            TypeSocialNetwork tSocial;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    tSocial = getByRow(row);
                    list.Add(tSocial);
                }
                tSocial = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static TypeSocialNetwork getByRow(DataRow row)
        {
            TypeSocialNetwork tSocial = new TypeSocialNetwork();
            tSocial.idTypeSocialNetwork = row.Field<int>("idTypeSocialNetwork");
            tSocial.name = row.Field<string>("name");
            tSocial.icon = row.Field<string>("icon");
            return tSocial;
        }
    }
}