/*
 * Copyright (c) 2011, The Board of Trustees of the Leland Stanford Junior 
 * University, creator Daniel L. Rubin. 
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package edu.stanford.hakan.aim4api.aimquery;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hakan
 */
class AimClass
{

	private String name;
	private String type;
	private final boolean isAbstract;
	private List<String> listXPaths;
	private List<AimProperty> listAimProperties;

	public AimClass(String name, String type, boolean isAbstract)
	{
		this.name = name;
		this.type = type;
		this.isAbstract = isAbstract;
		this.listXPaths = new ArrayList<String>();
		this.listAimProperties = new ArrayList<AimProperty>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public List<String> getListXPaths()
	{
		return listXPaths;
	}

	public void setListXPaths(List<String> listXPaths)
	{
		this.listXPaths = listXPaths;
	}

	public List<AimProperty> getListAimProperties()
	{
		return listAimProperties;
	}

	public void setListAimProperties(List<AimProperty> listAimProperties)
	{
		this.listAimProperties = listAimProperties;
	}

	public AimProperty getAimPropertyByName(String name)
	{
		for (int i = 0; i < this.listAimProperties.size(); i++) {
			if (this.listAimProperties.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
				return this.listAimProperties.get(i);
			}
		}
		return null;
	}
}
