using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class usersData
    {

        /*
         * public int id { get; set; }
        public string name { get; set; }
        public string lastName { get; set; }
        public int phone { get; set; }
        public string address { get; set; }
        public int promotions { get; set; }
        public string email { get; set; }
        public string password { get; set; }
        public int payments { get; set; }
         */

        public static void insert(users user) {
            string sInsert = "Insert into users (Nombre, lastName, phone, address, promotions, email, password, payments) values ('" + user.name + "','" + user.lastName + "'," + user.phone + ",'" + user.address + "'," + user.promotions + ",'" + user.email + "','" + user.password + "'," + user.payments + ")";
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void Update(users user) {
            string sUpdate = "update users set name='" + user.name + "',lastName='" + user.lastName + "'," + "phone="+ user.phone.toString() + ",address=" + user.address + ",promotions=" + user.promotions.toString() + ",email='"+ user.email + "',password='" + user.password + "',payments=" + user.payments.toString() + " where id=" + user.id.toString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static users getById(int id) {
            string select = "select * from users where id=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            users user;
            if (dt.Rows.Count > 0)
            {
                user = getByRow(dt.Rows[0]);
                return p;
            }
            return null;
        }

        public static List<Persona> ObtenerTodos()
        {
            string select = "select * from personas";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<users> list = new List<users>();
            Persona p;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    p = getByRow(row);
                    list.Add(p);
                }
                p = getByRow(dt.Rows[0]);
            }
            return lista;
        }

        private static user getByRow(DataRow row)
        {
            users user = new users();
            user.id = row.Field<int>("id");
            user.name = row.Field<string>("name");
            user.lastName = row.Field<string>("lastName");
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