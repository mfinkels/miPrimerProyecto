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
            string sInsert = "Insert into user (Nombre, lastName, photo, phone, address, promotions, email, password, payments) values ('" + user.name + "','" + user.lastName + "','" + user.photo + "'," + user.phone + ",'" + user.address + "'," + user.promotions + ",'" + user.email + "','" + user.password + "'," + user.payments + ")";
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void Update(User user) {
            string sUpdate = "update user set name='" + user.name + "',lastName='" + user.lastName + "',photo='" + user.photo + "',phone='"+ user.phone + "',address='" + user.address + "',promotions=" + Convert.ToString(user.promotions) + ",email='"+ user.email + "',password='" + user.password + "',payments=" + Convert.ToString(user.payments) + " where idUser=" + Convert.ToString(user.idUser);
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
            string select = "select * from personas";
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
            user.name = row.Field<string>("name");
            user.lastName = row.Field<string>("lastName");
            user.photo = row.Field<string>("photo");
            user.phone = row.Field<int>("phone");
            user.address = row.Field<string>("address");
            user.promotions = row.Field<int>("promotions");
            user.email = row.Field<string>("email");
            user.password = row.Field<string>("password");
            user.payments = row.Field<int>("payments");
            return user;
        }
    }
}