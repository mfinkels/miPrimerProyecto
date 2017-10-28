using ApiEjemplo.Models;
using ApiEjemplo.PromotionInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class BranchPromotionData
    {
        public static void insert(BranchPromotion bProm)
        {
            string[] row = new string[] { "idRestaurant", "idBranchRestaurant", "idPromotion" };
            object[] values = new object[] { bProm.idBranchRestaurant, bProm.idBranchRestaurant, bProm.idPromotion };
            string sInsert = QueryHelper.insert("branch_promotion", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(BranchPromotion bProm)
        {
            string[] row = new string[] { "idRestaurant", "idBranchRestaurant", "idPromotion" };
            object[] values = new object[] { bProm.idBranchRestaurant, bProm.idBranchRestaurant, bProm.idPromotion };
            string sUpdate = QueryHelper.update("branch_promotion", row, values, "idBranchPromotion", bProm.idBranchPromotion);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = QueryHelper.delete("branch_promotion", "idBranchPromotion", id);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static BranchPromotion getById(int id)
        {
            string select = "select * from branch_promotion where idBranchPromotion=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            BranchPromotion bProm;
            if (dt.Rows.Count > 0)
            {
                bProm = getByRow(dt.Rows[0]);
                return bProm;
            }
            return null;
        }

        public static List<BranchPromotion> getAll()
        {
            string select = "select * from branch_promotion";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<BranchPromotion> list = new List<BranchPromotion>();
            BranchPromotion bProm;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    bProm = getByRow(row);
                    list.Add(bProm);
                }
                bProm = getByRow(dt.Rows[0]);
            }
            return list;
        }
        public static List<Promotion> getAllByRestaurant(int id)
        {
            string select = "select * from branch_promotion where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Promotion> list = new List<Promotion>();
            BranchPromotion bProm;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    bProm = getByRow(row);
                    bProm.promotion = PromotionData.getById(bProm.idPromotion);
                    list.Add(bProm.promotion);
                }
                bProm = getByRow(dt.Rows[0]);
            }
            return list;
        }
        public static List<Promotion> getAllByBranch(int id)
        {
            string select = "select * from branch_promotion where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Promotion> list = new List<Promotion>();
            BranchPromotion bProm;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    bProm = getByRow(row);
                    bProm.promotion = PromotionData.getById(bProm.idPromotion);
                    list.Add(bProm.promotion);
                }
                bProm = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static BranchPromotion getByRow(DataRow row)
        {
            BranchPromotion bProm = new BranchPromotion();
            bProm.idBranchPromotion = row.Field<int>("idBranchPromotion");
            bProm.idRestaurant = row.Field<int>("idRestaurant");
            bProm.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            bProm.idPromotion = row.Field<int>("idPromotion");
            return bProm;
        }
    }
}