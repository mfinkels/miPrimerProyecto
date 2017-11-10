<?php

namespace API\APIBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use API\APIBundle\Entity\BranchRestaurant;

/**
 * BranchRestaurant controller.
 *
 */
class BranchRestaurantController extends Controller {

    /**
     * Lists all BranchRestaurant entities.
     *
     */
    public function getAction($idRestaurant, $id) {
        $em = $this->getDoctrine()->getManager();
        if ($id == 0) {
            $entities = $em->getRepository('APIAPIBundle:BranchRestaurant')->findBy(array('restaurant' => $idRestaurant));

            $respuesta = array();
            foreach ($entities as $entity) {
                array_push($respuesta, array('idBranchRestaurant' => $entity->getIdBranchRestaurant()
                    , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                    , 'name' => $entity->getName()
                    , 'latitude' => $entity->getLatitude()
                    , 'longitude' => $entity->getLongitude()
                    , 'averageCalification' => $entity->getAverageCalification()
                    , 'averageFood' => $entity->getAverageFood()
                    , 'averageService' => $entity->getAverageService()
                    , 'averageAmbience' => $entity->getAverageAmbience()
                    , 'restaurant' => array('name' => $entity->getRestaurant()->getName(),
                        'description' => $entity->getRestaurant()->getDescription())
                ));
            }
            $response = new Response(json_encode($respuesta));
        } else {

            $entity = $em->getRepository('APIAPIBundle:BranchRestaurant')->find($id);
            $response = new Response(json_encode(array('idBranchRestaurant' => $entity->getIdBranchRestaurant()
                        , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                        , 'name' => $entity->getName()
                        , 'latitude' => $entity->getLatitude()
                        , 'longitude' => $entity->getLongitude()
                        , 'averageCalification' => $entity->getAverageCalification()
                        , 'averageFood' => $entity->getAverageFood()
                        , 'averageService' => $entity->getAverageService()
                        , 'averageAmbience' => $entity->getAverageAmbience()
                        , 'restaurant' => array('name' => $entity->getRestaurant()->getName(),
                            'description' => $entity->getRestaurant()->getDescription())
                    )), 200);
        }
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * Edits an existing BranchRestaurant entity.
     *
     */
    public function putAction(Request $request, $id) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('APIAPIBundle:BranchRestaurant')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idBranchRestaurant' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }
        $restaurant = $em->getRepository('APIAPIBundle:Restaurant')->find($parameters['idRestaurant']);


        if (!isset($restaurant)) {
            $response = new Response(json_encode(array('idBranchRestaurant' => $id, 'error' => 'No se recibieron valores correctos de Restaurant')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setRestaurant($restaurant);
        $entity->setName($parameters['name']);
        $entity->setLatitude($parameters['latitude']);
        $entity->setLongitude($parameters['longitude']);
        $entity->setAverageCalification($parameters['averageCalification']);
        $entity->setAverageFood($parameters['averageFood']);
        $entity->setAverageService($parameters['averageService']);
        $entity->setAverageAmbience($parameters['averageAmbience']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idBranchRestaurant' => $entity->getIdBranchRestaurant()
                    , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                    , 'name' => $entity->getName()
                    , 'latitude' => $entity->getLatitude()
                    , 'longitude' => $entity->getLongitude()
                    , 'averageCalification' => $entity->getAverageCalification()
                    , 'averageFood' => $entity->getAverageFood()
                    , 'averageService' => $entity->getAverageService()
                    , 'averageAmbience' => $entity->getAverageAmbience()
                    , 'restaurant' => array('name' => $entity->getRestaurant()->getName(),
                        'description' => $entity->getRestaurant()->getDescription()))
                ), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Deletes a BranchRestaurant entity.
     *
     */
    public function deleteAction($id) {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('APIAPIBundle:BranchRestaurant')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idBranchRestaurant' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $em->remove($entity);
        $em->flush();

        $response = new Response(json_encode(array('idBranchRestaurant' => $id)), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Creates a new BranchRestaurant entity.
     *
     */
    public function postAction(Request $request) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }

        $entity = new BranchRestaurant();
        $em = $this->getDoctrine()->getManager();

        $restaurant = $em->getRepository('APIAPIBundle:Restaurant')->find($parameters['idRestaurant']);

        if (!isset($restaurant)) {
            $response = new Response(json_encode(array('error' => 'No se recibieron valores correctos de Restaurant')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setRestaurant($restaurant);
        $entity->setName($parameters['name']);
        $entity->setLatitude($parameters['latitude']);
        $entity->setLongitude($parameters['longitude']);
        $entity->setAverageCalification($parameters['averageCalification']);
        $entity->setAverageFood($parameters['averageFood']);
        $entity->setAverageService($parameters['averageService']);
        $entity->setAverageAmbience($parameters['averageAmbience']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idBranchRestaurant' => $entity->getIdBranchRestaurant()
                    , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                    , 'name' => $entity->getName()
                    , 'latitude' => $entity->getLatitude()
                    , 'longitude' => $entity->getLongitude()
                    , 'averageCalification' => $entity->getAverageCalification()
                    , 'averageFood' => $entity->getAverageFood()
                    , 'averageService' => $entity->getAverageService()
                    , 'averageAmbience' => $entity->getAverageAmbience()
                    , 'restaurant' => array('name' => $entity->getRestaurant()->getName(),
                        'description' => $entity->getRestaurant()->getDescription())
                )), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

}
