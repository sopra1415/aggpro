//
//  ViewController.m
//  Hashtags
//
//  Created by Frederik Riedel on 22.04.15.
//  Copyright (c) 2015 Frederik Riedel. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController
@synthesize textField;
- (void)viewDidLoad {
    [super viewDidLoad];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillChange:) name:UIKeyboardWillChangeFrameNotification object:nil];
    
    //    self.view.backgroundColor=[UIColor blackColor];
    
    
    CAGradientLayer *gradient = [CAGradientLayer layer];
    gradient.frame = self.view.bounds;
    gradient.colors = [NSArray arrayWithObjects:(id)[[UIColor colorWithRed:0 green:241.f/255.f blue:0 alpha:1.0] CGColor], (id)[[UIColor colorWithRed:2.f/255.f green:196.f/255.f blue:0 alpha:1.0] CGColor], nil];
    [self.view.layer insertSublayer:gradient atIndex:0];
    
    
    
    
    textField = [[UITextField alloc] initWithFrame:CGRectMake(0, self.view.frame.size.height-44, self.view.frame.size.width, 44)];
    textField.backgroundColor = [UIColor blackColor];
    textField.textColor=[UIColor whiteColor];
    textField.textAlignment=NSTextAlignmentCenter;
    [self.view addSubview:textField];
    textField.delegate=self;
   // textField.layer.cornerRadius=10.f;
   // textField.layer.masksToBounds=YES;
    textField.autocorrectionType=UITextAutocorrectionTypeNo;
    textField.autocapitalizationType=UITextAutocapitalizationTypeNone;
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(textFieldDidEndEditing:)
                                                 name:UITextFieldTextDidChangeNotification
                                               object:textField];
    //textField.keyboardType=UIKeyboardTypeTwitter;
    
    
    UIButton *sendHashTag = [UIButton buttonWithType:UIButtonTypeSystem];
    sendHashTag.frame=CGRectMake(100, 155, self.view.frame.size.width-200, 44);
    [sendHashTag setTitle:@"GO!" forState:UIControlStateNormal];
    sendHashTag.tintColor=[UIColor whiteColor];
    [self.view addSubview:sendHashTag];
    [sendHashTag addTarget:self action:@selector(sendHashTagToServer) forControlEvents:UIControlEventTouchUpInside];
}

-(void) sendHashTagToServer {
    NSString *udid = [UIDevice currentDevice].identifierForVendor.UUIDString;
    NSLog(@"%@",udid);
    NSString *escapedText = [[NSString stringWithFormat:@"{\"hashtag\":\"%@\",\"gps\":\"\",\"userID\":\"%@\"}",textField.text,udid] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString* restCallString = [NSString stringWithFormat:@"http://tobias-angerstein.de/mysql/send.php?json=%@&password=krautsalat5000", escapedText];
    NSLog(@"%@",restCallString);
    NSURL *restURL = [NSURL URLWithString:restCallString];
    NSURLRequest *restRequest = [NSURLRequest requestWithURL:restURL];
    
    [NSURLConnection sendAsynchronousRequest:restRequest queue:[NSOperationQueue mainQueue] completionHandler:^(NSURLResponse *response, NSData *data, NSError *error) {
        NSString *output = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
        NSLog(@"done: %@",output);
        textField.text=@"";
    }];
}

-(void)textFieldDidEndEditing:(id) notification {
    if([textField.text length]>0) {
        if([[textField.text substringToIndex:1] isEqualToString:@"#"]) {
            
        } else {
            textField.text=[NSString stringWithFormat:@"#%@",textField.text];
        }
    }
    //return YES;
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [self sendHashTagToServer];
    return YES;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



- (void)keyboardWillChange:(NSNotification *)notification {
    CGRect keyboardRect = [notification.userInfo[UIKeyboardFrameEndUserInfoKey] CGRectValue];
    keyboardRect = [self.view convertRect:keyboardRect fromView:nil]; //this is it!

    
    [UIView beginAnimations:nil context:NULL];
    [UIView setAnimationDuration:[notification.userInfo[UIKeyboardAnimationDurationUserInfoKey] doubleValue]];
    [UIView setAnimationCurve:[notification.userInfo[UIKeyboardAnimationCurveUserInfoKey] integerValue]];
    [UIView setAnimationBeginsFromCurrentState:YES];
    
    textField.frame=CGRectMake(0, self.view.frame.size.height - keyboardRect.size.height - 44, self.view.frame.size.width, 44);
    
    [UIView commitAnimations];
    
}

@end
