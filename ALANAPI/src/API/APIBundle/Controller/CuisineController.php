<?php

namespace API\APIBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use API\APIBundle\Entity\Cuisine;
use API\APIBundle\Form\CuisineType;

/**
 * Cuisine controller.
 *
 */
class CuisineController extends Controller {

    /**
     * Lists all Cuisine entities.
     *
     */
    public function getAction($id) {
        $em = $this->getDoctrine()->getManager();
        if ($id == 0) {
            $entities = $em->getRepository('APIAPIBundle:Cuisine')->findAll();

            //$response = new Response(json_encode($entities), 200);
            $respuesta = array();
            foreach ($entities as $entity) {
                array_push($respuesta, array('idCuisine' => $entity->getIdCuisine(), 'name' => $entity->getName()));
            }
            $response = new Response(json_encode($respuesta));
        } else {

            $entity = $em->getRepository('APIAPIBundle:Cuisine')->find($id);
            $response = new Response(json_encode(array('idCuisine' => $id, 'name' => $entity->getName())), 200);
        }
        $response->headers->set('Content-Type', 'application/json');

        return $response; // should be $reports as $doctrineobject is not serialized                    
    }

    /**
     * Edits an existing Cuisine entity.
     *
     */
    public function putAction(Request $request, $id) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('APIAPIBundle:Cuisine')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idCuisine' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setName($parameters['name']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idCuisine' => $id, 'name' => $entity->getName())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Deletes a Cuisine entity.
     *
     */
    public function deleteAction( $id) {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('APIAPIBundle:Cuisine')->find($id);
        
        if (!$entity) {
            $response = new Response(json_encode(array('idCuisine' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $em->remove($entity);
        $em->flush();
        
        $response = new Response(json_encode(array('idCuisine' => $id)), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Creates a new Cuisine entity.
     *
     */
    public function postAction(Request $request) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        
        $entity = new Cuisine();
        $em = $this->getDoctrine()->getManager();
        $entity->setName($parameters['name']);        
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idCuisine' => $entity->getIdCuisine(), 'name' => $entity->getName())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;

    }
}
