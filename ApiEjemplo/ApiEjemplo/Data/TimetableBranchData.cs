using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class TimetableBranchData
    {
        public static void insert(TimetableBranch ttBranch)
        {
            string sInsert = "Insert into timetable_branch (idBranchRestaurant, idDay, openingHour, closingHour) values (" + Convert.ToString(ttBranch.idBranchRestaurant) + "," + Convert.ToString(ttBranch.idDay) + ",'" + ttBranch.openingHour + "','" + ttBranch.closingHour + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(TimetableBranch ttBranch)
        {
            string sUpdate = "update timetable_branch set idBranchRestaurant=" + Convert.ToString(ttBranch.idBranchRestaurant) + ",idDay=" + Convert.ToString(ttBranch.idDay) + ",openingHour='" + ttBranch.openingHour + "',closingHour='" + ttBranch.closingHour + "' where idTimetableBranch=" + Convert.ToString(ttBranch.idTimetableBranch);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete * from timetable_branch where idTimetableBranch=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static TimetableBranch getById(int id)
        {
            string select = "select * from timetable_branch where idTimetableBranch=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            TimetableBranch ttBranch;
            if (dt.Rows.Count > 0)
            {
                ttBranch = getByRow(dt.Rows[0]);
                return ttBranch;
            }
            return null;
        }

        public static List<TimetableBranch> getAll()
        {
            string select = "select * from timetable_branch";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TimetableBranch> list = new List<TimetableBranch>();
            TimetableBranch ttBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    ttBranch = getByRow(row);
                    ttBranch.day = DayData.getById(ttBranch.idDay);
                    list.Add(ttBranch);
                }
                ttBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<TimetableBranch> getByBranch(int id)
        {
            string select = "select * from timetable_branch where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TimetableBranch> list = new List<TimetableBranch>();
            TimetableBranch ttBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    ttBranch = getByRow(row);
                    ttBranch.day = DayData.getById(ttBranch.idDay);
                    list.Add(ttBranch);
                }
                ttBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static TimetableBranch getByRow(DataRow row)
        {
            TimetableBranch ttBranch = new TimetableBranch();
            ttBranch.idTimetableBranch = row.Field<int>("idTimetableBranch");
            ttBranch.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            ttBranch.idDay = row.Field<int>("idDay");
            ttBranch.openingHour = row.Field<string>("openingHour");
            ttBranch.closingHour = row.Field<string>("closingHour");
            return ttBranch;
        }
    }
}