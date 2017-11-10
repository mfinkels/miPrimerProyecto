<?php

namespace API\APIBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use API\APIBundle\Entity\SocialNetworkRestaurant;

/**
 * SocialNetworkRestaurant controller.
 *
 */
class SocialNetworkRestaurantController extends Controller {

    /**
     * Lists all SocialNetworkRestaurant entities.
     *
     */
    public function getAction($idRestaurant, $idTypeSocialNetwork) {
        $em = $this->getDoctrine()->getManager();
        if ($idTypeSocialNetwork == 0) {
            $entities = $em->getRepository('APIAPIBundle:SocialNetworkRestaurant')->findBy(array('restaurant' => $idRestaurant));

            $respuesta = array();
            foreach ($entities as $entity) {
                array_push($respuesta, array('idSocialNetworkRestaurant' => $entity->getIdSocialNetworkRestaurant()
                    , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                    , 'idTypeSocialNetwork' => $entity->getTypeSocialNetwork()->getIdTypeSocialNetwork()
                    , 'value' => $entity->getValue()
                    , 'type' => array('name' => $entity->getTypeSocialNetwork()->getName(),
                        'icon' => $entity->getTypeSocialNetwork()->getIcon())
                ));
            }
            $response = new Response(json_encode($respuesta));
        } else {

            $entity = $em->getRepository('APIAPIBundle:SocialNetworkRestaurant')->findOneBy(array('restaurant' => $idRestaurant, 'typeSocialNetwork' => $idTypeSocialNetwork));
            $response = new Response(json_encode(array('idSocialNetworkRestaurant' => $entity->getIdSocialNetworkRestaurant()
                        , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                        , 'idTypeSocialNetwork' => $entity->getTypeSocialNetwork()->getIdTypeSocialNetwork()
                        , 'value' => $entity->getValue()
                        , 'type' => array('name' => $entity->getTypeSocialNetwork()->getName(),
                            'icon' => $entity->getTypeSocialNetwork()->getIcon())
                    )), 200);
            //json_encode($entity->getTypeSocialNetwork()
        }
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * Edits an existing SocialNetworkRestaurant entity.
     *
     */
    public function putAction(Request $request, $id) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('APIAPIBundle:SocialNetworkRestaurant')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idSocialNetworkRestaurant' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }
        $restaurant = $em->getRepository('APIAPIBundle:Restaurant')->find($parameters['idRestaurant']);
        $typeSocialNetwork = $em->getRepository('APIAPIBundle:TypeSocialNetwork')->find($parameters['idTypeSocialNetwork']);

        if (!isset($restaurant) || !isset($typeSocialNetwork)) {
            $response = new Response(json_encode(array('idSocialNetworkRestaurant' => $id, 'error' => 'No se recibieron valores correctos de Restaurant o de Tipo de Red Social')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setTypeSocialNetwork($typeSocialNetwork);
        $entity->setRestaurant($restaurant);
        $entity->setValue($parameters['value']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idSocialNetworkRestaurant' => $entity->getIdSocialNetworkRestaurant()
                    , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                    , 'idTypeSocialNetwork' => $entity->getTypeSocialNetwork()->getIdTypeSocialNetwork()
                    , 'value' => $entity->getValue()
                    , 'type' => array('name' => $entity->getTypeSocialNetwork()->getName(),
                        'icon' => $entity->getTypeSocialNetwork()->getIcon())
                )), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Deletes a SocialNetworkRestaurant entity.
     *
     */
    public function deleteAction($id) {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('APIAPIBundle:SocialNetworkRestaurant')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idSocialNetworkRestaurant' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $em->remove($entity);
        $em->flush();

        $response = new Response(json_encode(array('idSocialNetworkRestaurant' => $id)), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Creates a new SocialNetworkRestaurant entity.
     *
     */
    public function postAction(Request $request) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }

        $entity = new SocialNetworkRestaurant();
        $em = $this->getDoctrine()->getManager();

        $restaurant = $em->getRepository('APIAPIBundle:Restaurant')->find($parameters['idRestaurant']);
        $typeSocialNetwork = $em->getRepository('APIAPIBundle:TypeSocialNetwork')->find($parameters['idTypeSocialNetwork']);

        if (!isset($restaurant) || !isset($typeSocialNetwork)) {
            $response = new Response(json_encode(array('error' => 'No se recibieron valores correctos de Restaurant o de Tipo de Red Social')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setTypeSocialNetwork($typeSocialNetwork);
        $entity->setRestaurant($restaurant);
        $entity->setValue($parameters['value']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idSocialNetworkRestaurant' => $entity->getIdSocialNetworkRestaurant()
                    , 'idRestaurant' => $entity->getRestaurant()->getIdRestaurant()
                    , 'idTypeSocialNetwork' => $entity->getTypeSocialNetwork()->getIdTypeSocialNetwork()
                    , 'value' => $entity->getValue()
                    , 'type' => array('name' => $entity->getTypeSocialNetwork()->getName(),
                        'icon' => $entity->getTypeSocialNetwork()->getIcon())
                )), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

}
