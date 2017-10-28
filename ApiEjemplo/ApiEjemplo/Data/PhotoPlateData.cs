using ApiEjemplo.MenuInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class PhotoPlateData
    {
        public static void insert(PhotoPlate pPlate)
        {
            string sInsert = "Insert into photo_plate (idPlateMenu, idUser,photo) values (" + Convert.ToString(pPlate.idPlateMenu) + "," + Convert.ToString(pPlate.idUser) + ",'" + pPlate.photo + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(PhotoPlate pPlate)
        {
            string sUpdate = "update photo_plate set idPlateMenu=" + Convert.ToString(pPlate.idPlateMenu) + ",idUser=" + Convert.ToString(pPlate.idUser) + ",photo='" + pPlate.photo + "' where idPhotoPlate=" + Convert.ToString(pPlate.idPhotoPlate);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = "delete from photo_plate where idPhotoPlate=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static PhotoPlate getById(int id)
        {
            string select = "select * from photo_plate where idPhotoPlate=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            PhotoPlate pPlate;
            if (dt.Rows.Count > 0)
            {
                pPlate = getByRow(dt.Rows[0]);
                return pPlate;
            }
            return null;
        }

        public static List<PhotoPlate> getAll()
        {
            string select = "select * from photo_plate";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PhotoPlate> list = new List<PhotoPlate>();
            PhotoPlate pPlate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    pPlate = getByRow(row);
                    list.Add(pPlate);
                }
                pPlate = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<PhotoPlate> getPhotoByPlate(int id)
        {
            string select = "select * from photo_plate where idPlateMenu=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PhotoPlate> list = new List<PhotoPlate>();
            PhotoPlate pPlate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    pPlate = getByRow(row);
                    list.Add(pPlate);
                }
                pPlate = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static PhotoPlate getByRow(DataRow row)
        {
            PhotoPlate pPlate = new PhotoPlate();
            pPlate.idPhotoPlate = row.Field<int>("idPhotoPlate");
            pPlate.idPlateMenu = row.Field<int>("idPlateMenu");
            pPlate.idUser = row.Field<int>("idUser");
            pPlate.photo = row.Field<string>("photo");
            return pPlate;
        }
    }
}