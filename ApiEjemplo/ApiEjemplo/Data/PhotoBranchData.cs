using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class PhotoBranchData
    {
        public static void insert(PhotoBranch pBranch)
        {
            string sInsert = "Insert into photo_branch (idBranchRestaurant, idUser,photo) values (" + Convert.ToString(pBranch.idBranchRestaurant) + "," + Convert.ToString(pBranch.idUser) + ",'" + pBranch.photo + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(PhotoBranch pBranch)
        {
            string sUpdate = "update photo_branch set idBranchRestaurant=" + Convert.ToString(pBranch.idBranchRestaurant) + ",idUser=" + Convert.ToString(pBranch.idUser) + ",photo='" + pBranch.photo + "' where idBranchPhoto=" + Convert.ToString(pBranch.idBranchPhoto);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = "delete from photo_branch where idBranchPhoto=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static PhotoBranch getById(int id)
        {
            string select = "select * from photo_branch where idBranchPhoto=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            PhotoBranch pBranch;
            if (dt.Rows.Count > 0)
            {
                pBranch = getByRow(dt.Rows[0]);
                return pBranch;
            }
            return null;
        }

        public static List<PhotoBranch> getByBranch(int id)
        {
            string select = "select * from photo_branch where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PhotoBranch> list = new List<PhotoBranch>();
            PhotoBranch pBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    pBranch = getByRow(row);
                    list.Add(pBranch);
                }
                pBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<PhotoBranch> getAll()
        {
            string select = "select * from photo_branch";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PhotoBranch> list = new List<PhotoBranch>();
            PhotoBranch pBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    pBranch = getByRow(row);
                    list.Add(pBranch);
                }
                pBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static PhotoBranch getByRow(DataRow row)
        {
            PhotoBranch pBranch = new PhotoBranch();
            pBranch.idBranchPhoto = row.Field<int>("idPhotoBranch");
            pBranch.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            pBranch.idUser = row.Field<int>("idUser");
            pBranch.photo = row.Field<string>("photo");
            return pBranch;
        }
    }
}