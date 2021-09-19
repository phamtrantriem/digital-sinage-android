import { ApiProperty } from '@nestjs/swagger';

export class RegisterDisplayDto {
  @ApiProperty()
  hardwareKey: string;
  @ApiProperty()
  displayName: string;
  @ApiProperty()
  clientType: string;
  @ApiProperty()
  clientVersion: string;
  @ApiProperty()
  clientCode: string;
  @ApiProperty()
  operatingSystem: string;
  @ApiProperty()
  macAddress: string;
  @ApiProperty()
  xmrChannel: string;
  @ApiProperty()
  xmrPubKey: string;
}
