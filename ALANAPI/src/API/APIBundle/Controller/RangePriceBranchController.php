<?php

namespace API\APIBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use API\APIBundle\Entity\RangePriceBranch;
use API\APIBundle\Form\RangePriceBranchType;

/**
 * RangePriceBranch controller.
 *
 */
class RangePriceBranchController extends Controller {

    /**
     * Lists all RangePriceBranch entities.
     *
     */
    public function getAction($id) {
        $em = $this->getDoctrine()->getManager();
        if ($id == 0) {
            $entities = $em->getRepository('APIAPIBundle:RangePriceBranch')->findAll();

            $respuesta = array();
            foreach ($entities as $entity) {
                array_push($respuesta, array(
                    'idRangePriceBranch' => $entity->getIdRangePriceBranch(),
                    'idBranchRestaurant' => $entity->getIdBranchRestaurant(),
                    'minimum' => $entity->getMinimum(),
                    'maximum' => $entity->getMaximum()));
            }
            $response = new Response(json_encode($respuesta));
        } else {

            $entity = $em->getRepository('APIAPIBundle:RangePriceBranch')->find($id);
            $response = new Response(json_encode(array(
                        'idRangePriceBranch' => $entity->getIdRangePriceBranch(),
                        'idBranchRestaurant' => $entity->getIdBranchRestaurant(),
                        'minimum' => $entity->getMinimum(),
                        'maximum' => $entity->getMaximum())), 200);
        }
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * Edits an existing RangePriceBranch entity.
     *
     */
    public function putAction(Request $request, $id) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('APIAPIBundle:RangePriceBranch')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idRangePriceBranch' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setIdBranchRestaurant($parameters['idBranchRestaurant']);
        $entity->setMinimum($parameters['minimum']);
        $entity->setMaximum($parameters['maximum']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array(
                    'idRangePriceBranch' => $entity->getIdRangePriceBranch(),
                    'idBranchRestaurant' => $entity->getIdBranchRestaurant(),
                    'minimum' => $entity->getMinimum(),
                    'maximum' => $entity->getMaximum())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Deletes a RangePriceBranch entity.
     *
     */
    public function deleteAction($id) {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('APIAPIBundle:RangePriceBranch')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idRangePriceBranch' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $em->remove($entity);
        $em->flush();

        $response = new Response(json_encode(array('idRangePriceBranch' => $id)), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Creates a new RangePriceBranch entity.
     *
     */
    public function postAction(Request $request) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }

        $entity = new RangePriceBranch();
        $em = $this->getDoctrine()->getManager();
        $entity->setIdBranchRestaurant($parameters['idBranchRestaurant']);
        $entity->setMinimum($parameters['minimum']);
        $entity->setMaximum($parameters['maximum']);

        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array(
                    'idRangePriceBranch' => $entity->getIdRangePriceBranch(),
                    'idBranchRestaurant' => $entity->getIdBranchRestaurant(),
                    'minimum' => $entity->getMinimum(),
                    'maximum' => $entity->getMaximum())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

}
