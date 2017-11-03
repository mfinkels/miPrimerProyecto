using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class ServiceBranchData
    {
        public static void insert(ServiceBranch sBranch)
        {
            string sInsert = "Insert into service_branch (idBranchRestaurant, idService) values (" + Convert.ToString(sBranch.idBranchRestaurant) + "," + Convert.ToString(sBranch.idService) + ")";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(ServiceBranch sBranch)
        {
            string sUpdate = "update service_branch set idBranchRestaurant=" + Convert.ToString(sBranch.idBranchRestaurant) + ",idService=" + Convert.ToString(sBranch.idService) + " where idServiceBranch=" + Convert.ToString(sBranch.idServiceBranch);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from service_branch where idServiceBranch=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }


        public static ServiceBranch getById(int id)
        {
            string select = "select * from service_branch where idServiceBranch=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            ServiceBranch sBranch;
            if (dt.Rows.Count > 0)
            {
                sBranch = getByRow(dt.Rows[0]);
                return sBranch;
            }
            return null;
        }
       /*
        * public static List<SocialNetworkRestaurant> getAll()
        {
            string select = "select * from social_network_restaurant";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<SocialNetworkRestaurant> list = new List<SocialNetworkRestaurant>();
            SocialNetworkRestaurant snResto;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    snResto = getByRow(row);
                    snResto.type = TypeSocialNetworkData.getById(snResto.idTypeSocialNetwork);
                    list.Add(snResto);
                }
                snResto = getByRow(dt.Rows[0]);
            }
            return list;
        }
        */

       /* internal static IList<ServiceBranch> getAll()
        {
            string select = "SELECT * FROM `service_branch` ";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<ServiceBranch> list = new List<ServiceBranch>();
            ServiceBranch sBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    sBranch = getByRow(row);
                    sBranch.service = ServiceBranchData.getById(sBranch.);
                    list.Add(sBranch);
                }
                sBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }
        */

        public static List<Service> getServicesByRestaurants(int id)
        {
            string select = "select * from service_branch Where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Service> list = new List<Service>();
            ServiceBranch sBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    sBranch = getByRow(row);
                    sBranch.service = ServiceData.getById(sBranch.idService);
                    list.Add(sBranch.service);
                }
                sBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static ServiceBranch getByRow(DataRow row)
        {
            ServiceBranch sBranch = new ServiceBranch();
            sBranch.idServiceBranch = row.Field<int>("idServiceBranch");
            sBranch.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            sBranch.idService = row.Field<int>("idService");
            return sBranch;
        }
    }
}