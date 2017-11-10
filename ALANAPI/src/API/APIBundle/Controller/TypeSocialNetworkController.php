<?php

namespace API\APIBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use API\APIBundle\Entity\TypeSocialNetwork;
use API\APIBundle\Form\TypeSocialNetworkType;

/**
 * TypeSocialNetwork controller.
 *
 */
class TypeSocialNetworkController extends Controller
{

    /**
     * Lists all TypeSocialNetwork entities.
     *
     */
    public function getAction($id) {
        $em = $this->getDoctrine()->getManager();
        if ($id == 0) {
            $entities = $em->getRepository('APIAPIBundle:TypeSocialNetwork')->findAll();            
            $respuesta = array();
            foreach ($entities as $entity) {
                array_push($respuesta, array('idTypeSocialNetwork' => $entity->getIdTypeSocialNetwork(), 'name' => $entity->getName(), 'icon' => $entity->getIcon()));
            }
            $response = new Response(json_encode($respuesta));
        } else {
            $entity = $em->getRepository('APIAPIBundle:TypeSocialNetwork')->find($id);
            $response = new Response(json_encode(array('idTypeSocialNetwork' => $id, 'name' => $entity->getName(), 'icon' => $entity->getIcon())), 200);
        }
        $response->headers->set('Content-Type', 'application/json');

        return $response; 
    }

    /**
     * Edits an existing TypeSocialNetwork entity.
     *
     */
    public function putAction(Request $request, $id) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('APIAPIBundle:TypeSocialNetwork')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idTypeSocialNetwork' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setName($parameters['name']);
        $entity->setIcon($parameters['icon']);              
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idTypeSocialNetwork' => $id, 'name' => $entity->getName(),'icon' => $entity->getIcon())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Deletes a TypeSocialNetwork entity.
     *
     */
    public function deleteAction( $id) {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('APIAPIBundle:TypeSocialNetwork')->find($id);
        
        if (!$entity) {
            $response = new Response(json_encode(array('idTypeSocialNetwork' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $em->remove($entity);
        $em->flush();
        
        $response = new Response(json_encode(array('idTypeSocialNetwork' => $id)), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Creates a new TypeSocialNetwork entity.
     *
     */
    public function postAction(Request $request) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        
        $entity = new TypeSocialNetwork();
        $em = $this->getDoctrine()->getManager();
        $entity->setName($parameters['name']);        
        $entity->setIcon($parameters['icon']);        
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idTypeSocialNetwork' => $entity->getIdTypeSocialNetwork(), 'name' => $entity->getName(), 'icon' => $entity->getIcon())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;

    }
}
