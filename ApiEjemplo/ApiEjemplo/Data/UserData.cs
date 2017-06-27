using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class UserData
    {
        public static void insert(User user) {
            string[] row = new string[] { "idPromotion", "name", "lastName", "password", "latitude", "longitude", "photo", "phone", "email" };
            object[] values = new object[] { user.idPromotion, user.name, user.lastName, user.password, user.latitude, user.longitude, user.photo, user.phone, user.email };
            string sInsert = QueryHelper.insert("user", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void Update(User user) {
            string[] row = new string[] { "idPromotion", "name", "lastName", "password", "latitude", "longitude", "photo", "phone", "email" };
            object[] values = new object[] { user.idPromotion, user.name, user.lastName, user.password, user.latitude, user.longitude, user.photo, user.phone, user.email };
            string sUpdate = QueryHelper.update("user", row, values, "idUser", user.idUser);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = QueryHelper.delete("user", "idUser", id);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static User getById(int id) {
            string select = "select * from user where idUser=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            User user;
            if (dt.Rows.Count > 0)
            {
                user = getByRow(dt.Rows[0]);
                return user;
            }
            return null;
        }

        public static User getUser(string email, string password)
        {
            string select = "select * from user where email='" + email + "' AND password='" + password + "'";
            DataTable dt = DBHelper.EjecutarSelect(select);
            User user;
            if (dt.Rows.Count > 0)
            {
                user = getByRow(dt.Rows[0]);
                return user;
            }
            return null;
        }

        public static List<User> getAll()
        {
            string select = "select * from user";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<User> list = new List<User>();
            User user;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    user = getByRow(row);
                    list.Add(user);
                }
                user = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static User getByRow(DataRow row)
        {
            User user = new User();
            user.idUser = row.Field<int>("idUser");
            user.idPromotion = row.Field<int>("idPromotion");
            user.name = row.Field<string>("name");
            user.lastName = row.Field<string>("lastName");
            user.password = row.Field<string>("password");
            user.latitude = row.Field<string>("latitude");
            user.longitude = row.Field<string>("longitude");
            user.photo = row.Field<string>("photo");
            user.phone = row.Field<string>("phone");
            user.email = row.Field<string>("email");
            return user;
        }
    }
}