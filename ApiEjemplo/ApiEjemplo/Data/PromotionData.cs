using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class PromotionData
    {
        public static void insert(Promotion prom)
        {
            string sInsert = "Insert into promotion (idTypePromotion,code,name,startDate,expireDate,description,value) values (" + Convert.ToString(prom.idTypePromotion) + ",'" + prom.code + "','" + prom.name + "','" + prom.startDate.ToString("yyyy-MM-dd HH:mm") + "','" + prom.expireDate.ToString("yyyy-MM-dd HH:mm") + "','" + prom.description + "'," + Convert.ToString(prom.value) + ")";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(Promotion prom)
        {
            string sUpdate = "update promotion set idTypePromotion=" + Convert.ToString(prom.idTypePromotion) + ",code='" + prom.code + "',name='" + prom.name + "',startDate='" + prom.startDate.ToString("yyyy-MM-dd HH:mm") + "',expireDate='" + prom.expireDate.ToString("yyyy-MM-dd HH:mm") + "',description='" + prom.description + "',value=" + Convert.ToString(prom.value) + " where idPromotion=" + Convert.ToString(prom.idPromotion);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = "delete from promotion where idPromotion=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static Promotion getById(int id)
        {
            string select = "select * from promotion where idPromotion=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            Promotion prom;
            if (dt.Rows.Count > 0)
            {
                prom = getByRow(dt.Rows[0]);
                prom.type = TypePromotionData.getById(prom.idTypePromotion);
                return prom;
            }
            return null;
        }

        public static List<Promotion> getByIdUser(int id)
        {
            string select = "select promotion.* from promotion_user INNER JOIN promotion ON promotion_user.idPromotion=promotion.idPromotion where idUser=" + id;
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Promotion> list = new List<Promotion>();
            Promotion userProm;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    userProm = getByRow(row);
                    userProm.type = TypePromotionData.getById(userProm.idTypePromotion);
                    list.Add(userProm);
                }
                userProm = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<Promotion> getAll()
        {
            string select = "select * from promotion";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Promotion> list = new List<Promotion>();
            Promotion prom;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    prom = getByRow(row);
                    prom.type = TypePromotionData.getById(prom.idTypePromotion);
                    list.Add(prom);
                }
                prom = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static Promotion getByRow(DataRow row)
        {
            Promotion prom = new Promotion();
            prom.idPromotion = row.Field<int>("idPromotion");
            prom.idTypePromotion = row.Field<int>("idTypePromotion");
            prom.code = row.Field<string>("code");
            prom.name = row.Field<string>("name");
            prom.startDate = row.Field<DateTime>("startDate");
            prom.expireDate = row.Field<DateTime>("expireDate");
            prom.description = row.Field<string>("description");
            prom.value = row.Field<int>("value");
            return prom;
        }
    }
}