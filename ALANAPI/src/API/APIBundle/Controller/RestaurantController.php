<?php

namespace API\APIBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use API\APIBundle\Entity\Restaurant;

/**
 * Restaurant controller.
 *
 */
class RestaurantController extends Controller {

    /**
     * Lists all Restaurant entities.
     *
     */
    public function getAction($id) {
        $em = $this->getDoctrine()->getManager();
        if ($id == 0) {
            $entities = $em->getRepository('APIAPIBundle:Restaurant')->findAll();

            $respuesta = array();
            foreach ($entities as $entity) {
                array_push($respuesta, array('idRestaurant' => $entity->getIdRestaurant(),
                    'name' => $entity->getName(),
                    'description' => $entity->getDescription()
                ));
            }
            $response = new Response(json_encode($respuesta));
        } else {

            $entity = $em->getRepository('APIAPIBundle:Restaurant')->find($id);
            $response = new Response(json_encode(array('idRestaurant' => $entity->getIdRestaurant(),
                        'name' => $entity->getName(),
                        'description' => $entity->getDescription()
                    )), 200);
        }
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * Edits an existing Restaurant entity.
     *
     */
    public function putAction(Request $request, $id) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('APIAPIBundle:Restaurant')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idRestaurant' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setName($parameters['name']);
        $entity->setDescription($parameters['description']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idRestaurant' => $entity->getIdRestaurant(),
                    'name' => $entity->getName(),
                    'description' => $entity->getDescription()
                )), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Deletes a Restaurant entity.
     *
     */
    public function deleteAction($id) {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('APIAPIBundle:Restaurant')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idRestaurant' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $em->remove($entity);
        $em->flush();

        $response = new Response(json_encode(array('idRestaurant' => $id)), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Creates a new Restaurant entity.
     *
     */
    public function postAction(Request $request) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }

        $entity = new Restaurant();
        $em = $this->getDoctrine()->getManager();
        $entity->setName($parameters['name']);
        $entity->setDescription($parameters['description']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idRestaurant' => $entity->getIdRestaurant(),
                    'name' => $entity->getName(),
                    'description' => $entity->getDescription()
                )), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

}
