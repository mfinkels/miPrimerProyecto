using ApiEjemplo.PromotionInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class TypePromotionData
    {
        public static void insert(TypePromotion tProm)
        {
            string sInsert = "Insert into type_promotion (name) values ('" + tProm.name + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(TypePromotion tProm)
        {
            string sUpdate = "update type_promotion set name='" + tProm.name + "' where idTypePromotion=" + Convert.ToString(tProm.idTypePromotion);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from type_promotion where idTypePromotion=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static TypePromotion getById(int id)
        {
            string select = "select * from type_promotion where idTypePromotion=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            TypePromotion tProm;
            if (dt.Rows.Count > 0)
            {
                tProm = getByRow(dt.Rows[0]);
                return tProm;
            }
            return null;
        }

        public static List<TypePromotion> getAll()
        {
            string select = "select * from type_promotion";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TypePromotion> list = new List<TypePromotion>();
            TypePromotion tProm;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    tProm = getByRow(row);
                    list.Add(tProm);
                }
                tProm = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static TypePromotion getByRow(DataRow row)
        {
            TypePromotion tProm = new TypePromotion();
            tProm.idTypePromotion = row.Field<int>("idTypePromotion");
            tProm.name = row.Field<string>("name");
            return tProm;
        }
    }
}