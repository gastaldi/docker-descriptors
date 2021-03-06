/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.AddInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
class AddInstructionImpl extends AbstractDockerInstruction implements AddInstruction
{
   private String source;
   private String destination;

   public AddInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public AddInstruction source(String source)
   {
      this.source = source;
      return this;
   }

   @Override
   public AddInstruction destination(String dest)
   {
      this.destination = dest;
      return this;
   }

   @Override
   public String getSource()
   {
      return source;
   }

   @Override
   public String getDestination()
   {
      return destination;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (source == null || source.isEmpty())
         throw new IllegalStateException("Source is null or empty");
      if (destination == null || destination.isEmpty())
         throw new IllegalStateException("Destination is null or empty");
      writer.append("ADD ").append(source).append(" ").append(destination);
   }

   @Override
   public void read(String line)
   {
      String[] split = line.substring(4).split(" ");
      source(split[0]).destination(split[1]);
   }
}
