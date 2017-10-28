using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class RangePriceBranchData
    {
        public static void insert(RangePriceBranch rpBranch)
        {
            string sInsert = "Insert into range_price_branch (idBranchRestaurant, minimum, maximum) values (" + Convert.ToString(rpBranch.idBranchRestaurant) + "," + Convert.ToString(rpBranch.minimum) + "," + Convert.ToString(rpBranch.maximum) + ")";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(RangePriceBranch rpBranch)
        {
            string sUpdate = "update range_price_branch set idBranchRestaurant=" + Convert.ToString(rpBranch.idBranchRestaurant) + ",minimum=" + Convert.ToString(rpBranch.minimum) + ",maximum=" + Convert.ToString(rpBranch.maximum) + " where idRangePriceBranch=" + Convert.ToString(rpBranch.idRangePriceBranch);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = "delete from range_price_branch where idRangePriceBranch=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static RangePriceBranch getById(int id)
        {
            string select = "select * from range_price_branch where idRangePriceBranch=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            RangePriceBranch rpBranch;
            if (dt.Rows.Count > 0)
            {
                rpBranch = getByRow(dt.Rows[0]);
                return rpBranch;
            }
            return null;
        }

        public static List<RangePriceBranch> getAll()
        {
            string select = "select * from range_price_branch";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<RangePriceBranch> list = new List<RangePriceBranch>();
            RangePriceBranch rpBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    rpBranch = getByRow(row);
                    list.Add(rpBranch);
                }
                rpBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static RangePriceBranch getByRow(DataRow row)
        {
            RangePriceBranch rpBranch = new RangePriceBranch();
            rpBranch.idRangePriceBranch = row.Field<int>("idRangePriceBranch");
            rpBranch.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            rpBranch.minimum = row.Field<int>("minimum");
            rpBranch.maximum = row.Field<int>("maximum");
            return rpBranch;
        }
    }
}