import { Body, Controller, Get, Post } from '@nestjs/common';
import axios from 'axios';
import * as soap from 'soap';
import { AppService } from './app.service';
import { RegisterDisplayDto } from './dto/registerDisplay.dto';
import * as parser from 'fast-xml-parser';
import { parseString, parseStringPromise } from 'xml2js';
import { GetFileDto } from './dto/getFile.dto';
import { RequiredFileDto } from './dto/requiredFile.dto';
import { GetResourceDto } from './dto/getResouce.dto';
import { ScheduleDto } from './dto/schedule.dto';
console.log(soap);

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Post('register-display')
  async registerDisplay(@Body() body: RegisterDisplayDto) {
    console.log(body);
    try {
      const client = await soap.createClientAsync(
      'http://localhost/xmds.php?v=5&wsdl',
    );
    const data = await client?.RegisterDisplayAsync({
      ...body,
      serverKey: 'U0S92S62',
    });
    const response = await parseStringPromise(
      data?.[0]?.ActivationMessage?.$value,
    );
    return { response, data };
  } catch (err) {
    // console.log(err)
  }
    
  }
  @Post('get-resouce')
  async getResouce(@Body() body: GetResourceDto) {
    console.log(body);

    const client = await soap.createClientAsync(
      'http://localhost/xmds.php?v=5&wsdl',
    );
    const data = await client?.GetResourceAsync(body);
    const response = data?.[0]?.resource?.$value;

    return { response, data };
  }
  @Post('get-file')
  async getFile(@Body() body: GetFileDto) {
    console.log(body);

    const client = await soap.createClientAsync(
      'http://localhost/xmds.php?v=5&wsdl',
    );
    const data = await client?.getFileAsync({ ...body, serverKey: 'U0S92S62' });

    return { data };
  }
  @Post('required-file')
  async requiredFile(@Body() body: RequiredFileDto) {
    console.log(body);

    const client = await soap.createClientAsync(
      'http://localhost/xmds.php?v=5&wsdl',
    );
    try {
      const data = await client?.RequiredFilesAsync({
        ...body,
        serverKey: 'U0S92S62',
      });
      const response = await parseStringPromise(
        data?.[0]?.RequiredFilesXml?.$value,
      );
      return { data, response };
    } catch (error) {
      console.log(error);
    }
  }
  @Post('schedule')
  async schedule(@Body() body: ScheduleDto) {
    console.log(body);

    const client = await soap.createClientAsync(
      'http://localhost/xmds.php?v=5&wsdl',
    );
    console.log(Object.keys(client));
    try {
      const data = await client?.ScheduleAsync({
        ...body,
        serverKey: 'U0S92S62',
      });
      const response = await parseStringPromise(data?.[0]?.ScheduleXml?.$value);
      return { response, data };
    } catch (error) {
      console.log(error);
    }
  }
}
