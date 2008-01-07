/**
 * <copyright>
 * </copyright>
 *
 * $Id: Socks5Proxy.java 1 2006-06-12 17:37:42Z sxp $
 */
package edu.tsinghua.lumaqq.ecore.proxy;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Socks5 Proxy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getPassword <em>Password</em>}</li>
 *   <li>{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getPort <em>Port</em>}</li>
 *   <li>{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getServer <em>Server</em>}</li>
 *   <li>{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getUsername <em>Username</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.tsinghua.lumaqq.ecore.proxy.ProxyPackage#getSocks5Proxy()
 * @model
 * @generated
 */
public interface Socks5Proxy extends EObject {
	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see edu.tsinghua.lumaqq.ecore.proxy.ProxyPackage#getSocks5Proxy_Password()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #setPort(String)
	 * @see edu.tsinghua.lumaqq.ecore.proxy.ProxyPackage#getSocks5Proxy_Port()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getPort();

	/**
	 * Sets the value of the '{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(String value);

	/**
	 * Returns the value of the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server</em>' attribute.
	 * @see #setServer(String)
	 * @see edu.tsinghua.lumaqq.ecore.proxy.ProxyPackage#getSocks5Proxy_Server()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' attribute.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(String value);

	/**
	 * Returns the value of the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Username</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Username</em>' attribute.
	 * @see #setUsername(String)
	 * @see edu.tsinghua.lumaqq.ecore.proxy.ProxyPackage#getSocks5Proxy_Username()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getUsername();

	/**
	 * Sets the value of the '{@link edu.tsinghua.lumaqq.ecore.proxy.Socks5Proxy#getUsername <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Username</em>' attribute.
	 * @see #getUsername()
	 * @generated
	 */
	void setUsername(String value);

} // Socks5Proxy
