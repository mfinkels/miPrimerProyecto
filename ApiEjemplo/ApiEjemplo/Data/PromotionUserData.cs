using ApiEjemplo.UserInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class PromotionUserData
    {
        public static void Insert(PromotionUser userProm)
        {
            string sInsert = "Insert into promotion_user (idUser, idPromotion) values (" + Convert.ToString(userProm.idUser)  + ",'" + Convert.ToString(userProm.idPromotion) + ")";
            DBHelper.EjecutarIUD(sInsert);
        }

        public static void Update(PromotionUser userProm)
        {
            string sUpdate = "update promotion_user set idUser=" + Convert.ToString(userProm.idUser) + ",idPromotion=" + Convert.ToString(userProm.idPromotion) + "' where idPromotionUser=" + Convert.ToString(userProm.idPromotionUser);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static void Delete(int id)
        {
            string sUpdate = "delete from promotion_user where idPromotionUser=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static PromotionUser getById(int id)
        {
            string select = "select * from promotion_user where idPromotionUser=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            PromotionUser userProm;
            if (dt.Rows.Count > 0)
            {
                userProm = getByRow(dt.Rows[0]);
                return userProm;
            }
            return null;
        }

        public static List<PromotionUser> getAll()
        {
            string select = "select * from promotion_user";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PromotionUser> list = new List<PromotionUser>();
            PromotionUser userProm;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    userProm = getByRow(row);
                    list.Add(userProm);
                }
                userProm = getByRow(dt.Rows[0]);
            }
            return list;
        }
        public static List<PromotionUser> getAllByUser(int id)
        {
            string select = "select * from promotion_user where idUser=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PromotionUser> list = new List<PromotionUser>();
            PromotionUser userProm;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    userProm = getByRow(row);
                    list.Add(userProm);
                }
                userProm = getByRow(dt.Rows[0]);
            }
            return list;
        }
        private static PromotionUser getByRow(DataRow row)
        {
            PromotionUser userProm = new PromotionUser();
            userProm.idPromotionUser = row.Field<int>("idPromotionUser");
            userProm.idUser = row.Field<int>("idUser");
            userProm.idPromotion = row.Field<int>("idPromotion");
            return userProm;
        }
    }
}